package com.wsl.mypokemonapp.common

import androidx.annotation.StringRes
import com.wsl.utils.Failure

sealed class BaseScreenEvent {
    data class ShowSnackbar(@StringRes val msg: Int) : BaseScreenEvent()
    data class HandleFailure(val failure: Failure) : BaseScreenEvent()
    object PopBackStack : BaseScreenEvent()
    object HideKeyboard : BaseScreenEvent()
    object ShowKeyboard : BaseScreenEvent()
    object RebootApp: BaseScreenEvent()
}