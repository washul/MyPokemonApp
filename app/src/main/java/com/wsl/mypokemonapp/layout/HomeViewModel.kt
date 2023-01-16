package com.wsl.mypokemonapp.layout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.usecases.GetPokemonListUseCase
import com.wsl.domain.pokemon.usecases.GetPokemonUseCase
import com.wsl.mypokemonapp.R
import com.wsl.mypokemonapp.base.viewmodel.BaseViewModel
import com.wsl.mypokemonapp.utils.addList
import com.wsl.utils.onFailure
import com.wsl.utils.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonListUseCase: GetPokemonListUseCase
): BaseViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val mutableShowLoading = MutableLiveData<Boolean>(false)
    var showLoading = mutableShowLoading

    private var offsetItemCount = 0
    private val limitItems = 10
    private var totalItemsCount = 100000

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        mutableShowLoading.postValue(true)
        viewModelScope.launch {
            getPokemonListUseCase(
                GetPokemonListUseCase.Params(
                    limitItems,
                    offsetItemCount
                )
            )
                .onSuccess {
                    totalItemsCount = it.count

                    //Build list of calls
                    val itemList = ArrayList<Pokemon>()
                    it.results.subList(offsetItemCount, limitItems).map {  resource ->
                        withContext(Dispatchers.Default) {
                            getPokemon(resource.id.toString()) { pokemon ->
                                itemList.add(pokemon)
                            }
                        }
                    }


//                    it.results.map { resource ->
//                        withContext(Dispatchers.Default) {
//                            getPokemon(resource.id.toString()) { pokemon ->
//                                itemList.add(pokemon)
//                            }
//                        }
//                    }
                    offsetItemCount += limitItems
                    _pokemonList.addList(itemList)
                    mutableShowLoading.postValue(false)
                }
                .onFailure {
                    showSnackbar(R.string.problem_try_again)
                    mutableShowLoading.postValue(false)
                }
        }
    }

    private suspend fun getPokemon(idOrName: String, fu: (Pokemon) -> Unit) {

        getPokemonUseCase(
            GetPokemonUseCase.Params(
                idOrName
            )
        )
            .onSuccess {
                 fu(it)
            }
            .onFailure { handleFailure(it) }
    }


}