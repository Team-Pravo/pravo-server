package com.pravo.pravo.domain.promise.controller

import com.pravo.pravo.domain.promise.dto.request.PromiseSearchDto
import com.pravo.pravo.domain.promise.dto.response.PromiseResponseDto
import com.pravo.pravo.domain.promise.service.PromiseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/promise")
class PromiseController(
    private val promiseService: PromiseService
) : PromiseApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getPromisesByMember(
        @RequestParam memberId: Long,
        @RequestParam request: PromiseSearchDto?
    ): List<PromiseResponseDto> {
        // TODO: 토큰 기반 유저 인증 추가 및 유저 401 에러 처리
        return promiseService.getPromisesByMember(memberId, request?.startedAt, request?.endedAt)
    }
}
