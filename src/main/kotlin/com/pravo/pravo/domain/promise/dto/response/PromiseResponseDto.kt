package com.pravo.pravo.domain.promise.dto.response

import com.pravo.pravo.domain.promise.model.Promise
import com.pravo.pravo.domain.promise.model.enums.PromiseStatus
import java.time.LocalDateTime

data class PromiseResponseDto(
    val id: Long,
    val name: String,
    val promiseDate: LocalDateTime,
    val location: String?,
    val status: PromiseStatus,
) {
    companion object {
        fun of(promise: Promise): PromiseResponseDto {
            return PromiseResponseDto(
                id = promise.id,
                name = promise.name,
                promiseDate = promise.promiseDate,
                location = promise.location,
                status = promise.status,
            )
        }
    }
}
