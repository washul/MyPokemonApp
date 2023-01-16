package com.wsl.mypokemonapp.layout.favorites

import android.annotation.SuppressLint
import com.wsl.mypokemonapp.R
import com.wsl.mypokemonapp.base.viewmodel.BaseViewModelFragment
import com.wsl.mypokemonapp.common.LayoutFragment
import com.wsl.mypokemonapp.databinding.FragmentFavoriteBinding

@SuppressLint("NonConstantResourceId")
@LayoutFragment(R.layout.fragment_favorite)
class FavoriteFragment :  BaseViewModelFragment<FragmentFavoriteBinding, FavoriteViewModel>
    (FavoriteViewModel::class) {



}