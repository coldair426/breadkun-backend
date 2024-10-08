package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.service.CafeCartQueryService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeCartQueryHandler(
    private val cafeCartQueryService: CafeCartQueryService
) {
    suspend fun findActiveCafeCartById(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val result = cafeCartQueryService.findActiveCafeCartById(cafeCartId)

        return ResponseUtils.ok(result)
    }
    suspend fun findActiveCafeCartByOptions(request: ServerRequest): ServerResponse {
        val createdById = request.queryParam("createdById").getOrNull()?.takeIf { it.isNotBlank() }

        val result = cafeCartQueryService.findActiveCafeCartByOptions(createdById)

        return ResponseUtils.ok(result)
    }
}