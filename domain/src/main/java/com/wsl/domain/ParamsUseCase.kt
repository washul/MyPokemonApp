package com.wsl.domain

import com.wsl.utils.Failure
import com.wsl.utils.Result

internal interface ParamsUseCase<T, Params> {
    suspend operator fun invoke(params: Params): Result<Failure, T>
}