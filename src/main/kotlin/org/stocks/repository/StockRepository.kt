package org.stocks.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.stocks.entity.Stock

interface StockRepository : JpaRepository<Stock, Long> {

}