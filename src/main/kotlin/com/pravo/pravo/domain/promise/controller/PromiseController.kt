package com.pravo.pravo.domain.promise.controller

import com.pravo.pravo.domain.promise.service.PromiseService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class PromiseController(private val promiseService: PromiseService)
