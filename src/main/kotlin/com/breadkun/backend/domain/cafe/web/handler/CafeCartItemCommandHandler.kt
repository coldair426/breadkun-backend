package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.domain.cafe.service.CafeCartItemCommandService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class CafeCartItemCommandHandler(
    private val cafeCartItemCommandService: CafeCartItemCommandService
) {
    suspend fun createCafeCartItems(request: ServerRequest): ServerResponse {
        val cartId = request.pathVariable("cartId")
        val userUUID = request.headers().firstHeader("X-User-UUID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-UUID header")
        val cafeCartItemCreateDTOs = request.awaitBody<List<CafeCartItemCreateDTO>>()

        val createdCartItems = cafeCartItemCommandService.createCafeCartItems(cartId, userUUID, cafeCartItemCreateDTOs)

        return ResponseUtils.ok(createdCartItems)
    }
}