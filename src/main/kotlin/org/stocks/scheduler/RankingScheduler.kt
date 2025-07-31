package org.stocks.scheduler

import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.stocks.service.RankingService
import java.util.concurrent.TimeUnit

@Component
class RankingScheduler(
    private val rankingService: RankingService
) {
    private val logger = KotlinLogging.logger {}

    @Scheduled(fixedRateString = "\${ranking.interval-minutes:5}", timeUnit = TimeUnit.MINUTES)
    fun runRankingBatch() {
        try {
            logger.info("Ranking Batch Scheduler Start")
            rankingService.generateRankings()
            logger.info("Ranking Batch Scheduler End")
        } catch (e: Exception) {
            logger.error("Ranking Batch Scheduler Error : ${e.message} stack : ${e.stackTraceToString()}")
        }
    }
}