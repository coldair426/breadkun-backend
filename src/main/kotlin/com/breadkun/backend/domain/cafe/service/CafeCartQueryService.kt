package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.response.CafeCartDTO
import com.breadkun.backend.domain.cafe.repository.CafeCartQueryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

interface CafeCartQueryService {
    suspend fun findActiveCafeCartById(cafeCartId: String): CafeCartDTO?
    suspend fun findActiveCafeCartByOptions(createdById: String?): List<CafeCartDTO>
}

@Service
class CafeCartQueryServiceImpl(
    private val cafeCartQueryRepository: CafeCartQueryRepository
) : CafeCartQueryService {
    override suspend fun findActiveCafeCartById(cafeCartId: String): CafeCartDTO? {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryRepository.findActiveById(cafeCartId, currentTime)
            ?.let {
                CafeCartDTO.fromModel(it)
            }
    }

    override suspend fun findActiveCafeCartByOptions(createdById: String?): List<CafeCartDTO> {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryRepository.findActiveByMultipleOptions(createdById, currentTime)
            .map {
                CafeCartDTO.fromModel(it)
            }
    }
}