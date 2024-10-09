package com.pravo.pravo.domain.promise.repository

import com.pravo.pravo.domain.promise.model.Promise
import org.springframework.data.jpa.repository.JpaRepository

interface PromiseRepository : JpaRepository<Promise, Long>
