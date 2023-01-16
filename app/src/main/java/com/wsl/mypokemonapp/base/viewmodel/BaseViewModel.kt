package com.wsl.mypokemonapp.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wsl.mypokemonapp.common.BaseScreenEvent
import com.wsl.utils.Failure
import org.koin.core.component.KoinComponent

abstract class BaseViewModel: ViewModel(), KoinComponent {

    private val _baseScreenEvent = MutableLiveData<BaseScreenEvent>()
    var baseScreenEvent: LiveData<BaseScreenEvent> = _baseScreenEvent

    protected fun handleFailure(failure: Failure) {
        failure.logError()
        postBaseScreenEvent(BaseScreenEvent.HandleFailure(failure))
    }

    protected fun showSnackbar(@StringRes stringRes: Int) =
        postBaseScreenEvent(BaseScreenEvent.ShowSnackbar(stringRes))

    private fun postBaseScreenEvent(event: BaseScreenEvent) =
        _baseScreenEvent.postValue(event)
}