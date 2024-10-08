package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.dto.request.CafeCartCreateDTO
import com.breadkun.backend.domain.cafe.service.CafeCartCommandService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class CafeCartCommandHandler(
    private val cafeCartCommandService: CafeCartCommandService
) {
    suspend fun createCafeCart(request: ServerRequest): ServerResponse {
        val userUUID = request.headers().firstHeader("X-User-UUID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-UUID header")
        val cafeCartCreateDTO = request.awaitBody<CafeCartCreateDTO>()
        val createdCart = cafeCartCommandService.createCafeCart(userUUID, cafeCartCreateDTO)

        return ResponseUtils.ok(createdCart)
    }
}