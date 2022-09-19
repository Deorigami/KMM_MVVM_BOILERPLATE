

package dev.shared.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseRespondDto<T>(
    val status: Int,
    val data: T
) {
    companion object {
        fun <T> BaseRespondDto<*>.toBaseRespondEntity(data : T) : BaseRespondEntity<T> {
            return BaseRespondEntity(data)
        }
    }
}