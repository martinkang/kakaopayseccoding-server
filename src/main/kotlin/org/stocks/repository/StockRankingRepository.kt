package org.stocks.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.stocks.entity.StockRanking

interface StockRankingRepository : JpaRepository<StockRanking, Long> {
}