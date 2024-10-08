package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCartItem
import com.breadkun.backend.domain.cafe.model.CafeMenu
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenu, String> {}

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItem, String> {
    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItem>
}