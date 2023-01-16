package com.wsl.mypokemonapp.layout.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wsl.domain.pokemon.model.NamedApiResourceList
import com.wsl.domain.pokemon.model.Pokemon
import com.wsl.domain.pokemon.usecases.DeleteFavoriteUseCase
import com.wsl.domain.pokemon.usecases.GetPokemonListUseCase
import com.wsl.domain.pokemon.usecases.GetPokemonUseCase
import com.wsl.domain.pokemon.usecases.SetFavoriteUseCase
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
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
): BaseViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _searchPokemonList = MutableLiveData<List<Pokemon>>()
    val searchPokemonList: LiveData<List<Pokemon>> = _searchPokemonList

    private val mutableShowLoading = MutableLiveData<Boolean>(false)
    var showLoading = mutableShowLoading

    private lateinit var namedApiResourceList: NamedApiResourceList
    private var offsetItemCount = 0
    private var limitItems = 10
    private var totalItemsCount = 100000

    init {
        getPokemonList()
    }

//    puedes filtrar por tipo, obteninedo los 20 tipos y creando una lista de opciones
//    despues hacer una llamada de filterByType y enviar el numero de type como 9 para fire
//    este te regresa un objeto distinto (Type), pero tiene una lista de pokemons con lo que puedes
//    crear una lista como la que ya tienes

    private fun getPokemonList() {
        mutableShowLoading.postValue(true)
        viewModelScope.launch {
            getPokemonListUseCase(
                GetPokemonListUseCase.Params(
                    totalItemsCount,
                    offsetItemCount
                )
            )
                .onSuccess {
                    totalItemsCount = it.count
                    namedApiResourceList = it
                    createPokemonList()
                }
                .onFailure {
                    showSnackbar(R.string.problem_try_again)
                    mutableShowLoading.postValue(false)
                }
        }
    }

    fun createPokemonList() {
        //Build list of calls
        if (limitItems >= totalItemsCount) return

        viewModelScope.launch {
            val itemList = ArrayList<Pokemon>()
            namedApiResourceList.results.subList(offsetItemCount, limitItems).map {  resource ->
                withContext(Dispatchers.Default){
                    getPokemon(resource.id.toString()) { pokemon ->
                        itemList.add(pokemon)
                    }
                }

            }

            offsetItemCount += limitItems
            limitItems += limitItems
            _pokemonList.addList(itemList)
            mutableShowLoading.postValue(false)
        }
    }

    fun searchPokemon(query: String) {
        mutableShowLoading.postValue(true)
        viewModelScope.launch {
            val itemList = ArrayList<Pokemon>()
            namedApiResourceList.results.filter { it.name.contains(query) }.map { resource ->
                withContext(Dispatchers.Default) {
                    getPokemon(resource.id.toString()) { pokemon ->
                        itemList.add(pokemon)
                    }
                }
            }

            _searchPokemonList.postValue(itemList)
            mutableShowLoading.postValue(false)
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


    /**
     * Favorites functionality
     * */

    fun setFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            setFavoriteUseCase(
                SetFavoriteUseCase.Params(
                    pokemon
                )
            )
                .onFailure { handleFailure(it) }
                .onSuccess { /*Everything ok*/ }
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            deleteFavoriteUseCase(
                DeleteFavoriteUseCase.Params(
                    id
                )
            )
                .onFailure { handleFailure(it) }
                .onSuccess { /*Everything ok*/ }
        }
    }

}