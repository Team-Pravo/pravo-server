package com.pravo.pravo.domain.promise.controller

import com.pravo.pravo.domain.promise.dto.response.PromiseResponseDto
import com.pravo.pravo.domain.promise.service.PromiseService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/promise")
@RequiredArgsConstructor
class PromiseController(private val promiseService: PromiseService) {
    @GetMapping
    fun getPromisesByMember(
        @RequestParam memberId: Long,
        @RequestParam(required = false) startedAt: LocalDate?,
        @RequestParam(required = false) endedAt: LocalDate?,
    ): List<PromiseResponseDto> {
        return promiseService.getPromisesByMember(memberId, startedAt, endedAt)
    }
}
