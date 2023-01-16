package com.wsl.mypokemonapp.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>>.addList(values: List<T>) {
    val value = this.value ?: arrayListOf()
    val arrayList = ArrayList<T>(value)
    arrayList.addAll(values)
    this.postValue(arrayList)
}

