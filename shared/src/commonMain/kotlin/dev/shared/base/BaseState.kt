/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.base

sealed class BaseState<out R> {
    object Loading : BaseState<Nothing>()
    data class Success<R>(val data : R) : BaseState<R>()
    data class Error(val data: Throwable) : BaseState<Nothing>()
}