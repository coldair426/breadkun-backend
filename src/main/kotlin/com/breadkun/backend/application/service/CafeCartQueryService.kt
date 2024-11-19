package com.breadkun.backend.application.service

import com.breadkun.backend.application.port.input.CafeCartItemQueryUseCase
import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartQueryPort
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CafeCartQueryService(
    private val cafeCartQueryPort: CafeCartQueryPort,
    private val cafeCartItemQueryUseCase: CafeCartItemQueryUseCase
) : CafeCartQueryUseCase {
    override suspend fun findCafeCartsByOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?
    ): List<CafeCart> {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryPort.findByMultipleOptions(cafeLocation, status, createdById, currentTime)
            .map {
                CafeCart.fromEntity(it)
            }
    }

    override suspend fun findCafeCartById(
        cafeCartId: String
    ): CafeCart? {
        return cafeCartQueryPort.findById(cafeCartId)?.let { CafeCart.fromEntity(it) } ?: return null
    }
}