package com.pravo.pravo.domain.promise.service

import com.pravo.pravo.domain.promise.repository.PromiseRepository
import org.springframework.stereotype.Service

@Service
class PromiseService(private val promiseRepository: PromiseRepository)
