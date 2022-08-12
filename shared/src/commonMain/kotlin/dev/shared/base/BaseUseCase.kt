/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseUseCase <P, R> {

    abstract val default : R
    abstract suspend fun build(param: P) : BaseRespondEntity<R>
    private var job : Job? = null

    fun execute(
        param: P,
        scope: CoroutineScope,
        onResult: (BaseState<R>) -> Unit
    ){
        job = scope.launch {
            onResult(either {
                build(param)
            })
        }
    }

    fun cancel() = job?.cancel()

    private suspend fun <V> either(block : suspend () -> BaseRespondEntity<V>) : BaseState<V> = kotlin.runCatching {
        BaseState.Success(block().data)
    }.getOrElse {
        BaseState.Error(it)
    }
}