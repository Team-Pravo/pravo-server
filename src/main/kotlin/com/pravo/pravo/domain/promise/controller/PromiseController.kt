package com.pravo.pravo.domain.promise.controller

import com.pravo.pravo.domain.promise.dto.request.PromiseSearchDto
import com.pravo.pravo.domain.promise.dto.response.PromiseResponseDto
import com.pravo.pravo.domain.promise.service.PromiseService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/promise")
@RequiredArgsConstructor
class PromiseController(private val promiseService: PromiseService) {
    @GetMapping
    fun getPromisesByMember(
        @ModelAttribute request: PromiseSearchDto,
        @RequestParam memberId: Long,
    ): List<PromiseResponseDto> {
        return promiseService.getPromisesByMember(memberId, request.startedAt, request.endedAt)
    }
}
