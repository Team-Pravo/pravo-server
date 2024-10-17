package com.pravo.pravo.domain.promise.dto.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class PromiseSearchDto(
    @DateTimeFormat(pattern = "yy/MM/dd")
    val startedAt: LocalDate? = null,
    @DateTimeFormat(pattern = "yy/MM/dd")
    val endedAt: LocalDate? = null,
)
