package com.wsl.mypokemonapp.layout.home


import android.annotation.SuppressLint
import android.view.View
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wsl.mypokemonapp.R
import com.wsl.mypokemonapp.base.viewmodel.BaseViewModelFragment
import com.wsl.mypokemonapp.common.BaseScreenEvent
import com.wsl.mypokemonapp.common.LayoutFragment
import com.wsl.mypokemonapp.databinding.FragmentHomeBinding
import com.wsl.mypokemonapp.ui.ItemListAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

@SuppressLint("NonConstantResourceId")
@LayoutFragment(R.layout.fragment_home)
class HomeFragment : BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>
    (HomeViewModel::class){

    private lateinit var listAdapter: ItemListAdapter
    private var isSearching = false

    override fun subscribeToViewModel(viewModel: HomeViewModel) {
        createRecycler()
        setUpSearchView()

        viewModel.baseScreenEvent.observe(viewLifecycleOwner) {
            when(it){
                is BaseScreenEvent.HandleFailure -> {

                }
                is BaseScreenEvent.ShowSnackbar -> {
                    showSnackBar(it.msg)
                }
                else -> { }
            }
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }

        viewModel.searchPokemonList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun createRecycler() {
        listAdapter = ItemListAdapter { itemId ->

        }
        binding.root.homeItemList.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 1)
        }.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState== RecyclerView.SCROLL_STATE_IDLE && !isSearching){
                    viewModel.createPokemonList()
                }
            }
        })
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    if (text.length > 2 ){
                        isSearching = true
                        viewModel.searchPokemon(text)
                    } else if (text == "") {
                        listAdapter.submitList(viewModel.pokemonList.value)
                        binding.searchView.clearFocus()
                        isSearching = false
                    }
                }
                return false
            }

        })
    }



    private fun showLoading(show: Boolean ){
        binding.homeLoading.visibility = if ( show ) View.VISIBLE else View.GONE
    }

    private fun showSnackBar(@StringRes msg: Int) {
        view?.let {  Snackbar.make(it, msg, Snackbar.LENGTH_LONG).show() }
    }

}