package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.application.port.input.CafeCartCommandUseCase
import org.springframework.stereotype.Service

@Service
class CafeCartCommandService(
    private val cafeCartCommandPort: com.breadkun.backend.application.port.output.CafeCartCommandPort
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(userUUID: String, dto: com.breadkun.backend.application.dto.CafeCartCreateDTO): CafeCart {
        return cafeCartCommandPort.save(CafeCart.fromCreateDTO(userUUID, dto).toEntity())
            .let {
                CafeCart.fromEntity(it)
            }
    }
}