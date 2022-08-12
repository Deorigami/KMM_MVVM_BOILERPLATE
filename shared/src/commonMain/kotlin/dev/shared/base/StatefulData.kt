/*
 * Copyright 2022 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shared.base

import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StatefulData<P, R>(
    private val useCase: BaseUseCase<P, R>,
    private val scope: CoroutineScope
) {
    private val _state = MutableStateFlow<BaseState<R>>(BaseState.Loading)
    val state : CStateFlow<BaseState<R>> get() = _state.cStateFlow()

    fun loadData(param: P){
        scope.launch {
            useCase.execute(
                param,
                scope,
            ){
                _state.value = it
            }
        }
    }
}