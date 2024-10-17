package com.pravo.pravo.domain.promise.dto.request

import java.time.LocalDateTime

data class PromiseCreateDto(
    val name: String,
    val promiseDate: LocalDateTime,
    val location: String,
)
