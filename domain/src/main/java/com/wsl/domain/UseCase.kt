package com.wsl.domain

import com.wsl.utils.Result
import com.wsl.utils.Failure

internal interface UseCase<T> {
    suspend operator fun invoke(): Result<Failure, T>
}
