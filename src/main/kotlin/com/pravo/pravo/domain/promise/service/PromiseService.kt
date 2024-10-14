package com.pravo.pravo.domain.promise.service

import com.pravo.pravo.domain.promise.dto.response.PromiseResponseDto
import com.pravo.pravo.domain.promise.repository.PromiseRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PromiseService(private val promiseRepository: PromiseRepository) {
    fun getPromiseById(id: Long) = promiseRepository.findById(id)

    fun getPromisesByMember(
        memberId: Long,
        startedAt: LocalDate?,
        endedAt: LocalDate?,
    ): List<PromiseResponseDto> {
        return promiseRepository.getPromisesByMemberIdAndStartedAtAndEndedAt(memberId, startedAt, endedAt)
            .map(PromiseResponseDto::of)
    }
}
