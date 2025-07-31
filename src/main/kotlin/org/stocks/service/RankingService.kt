package org.stocks.service

import org.springframework.stereotype.Service
import org.stocks.entity.StockRanking
import org.stocks.enum.RankingTag
import org.stocks.repository.StockRankingRepository
import org.stocks.repository.StockRepository

@Service
class RankingService(
    private val stockRepository: StockRepository,
    private val stockRankingRepository: StockRankingRepository
) {
    fun generateRankings() {
        val rankingData = stockRepository.findAll()

        RankingTag.values().forEach { tag ->

            val stockRanking = when (tag) {
                RankingTag.POPULARITY -> rankingData // 그냥 그대로
                RankingTag.GAIN -> rankingData.sortedByDescending { it.priceChange }
                RankingTag.LOSS -> rankingData.sortedBy { it.priceChange }
                RankingTag.VOLUME -> rankingData.sortedByDescending { it.volume }
            }

            val stockRankingList = stockRanking.map { stock ->
                StockRanking(
                    tag = tag,
                    timeTag = "",
                    stockCode = stock.code,
                    price = stock.price,
                    volume =  stock.volume,
                    priceChange = stock.priceChange
                )
            }

            stockRankingRepository.saveAll(stockRankingList)
        }
    }
}