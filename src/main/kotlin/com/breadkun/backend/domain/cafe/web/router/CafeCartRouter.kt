package com.breadkun.backend.domain.cafe.web.router

import com.breadkun.backend.domain.cafe.web.handler.CafeCartCommandHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeCartRouterConfig(
    private val cafeCartCommandHandler: CafeCartCommandHandler,
) {
    @Bean
    fun cafeCartRouter() = coRouter {
        "/api/cafe/carts".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                POST("", cafeCartCommandHandler::createCafeCart)
            }
        }
    }
}