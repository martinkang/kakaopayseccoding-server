package org.stocks.runner

import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.stocks.entity.Stock
import org.stocks.repository.StockRepository
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class DataLoader(
    private val stockRepository: StockRepository
) : CommandLineRunner {

    private val logger = KotlinLogging.logger {}

    override fun run(vararg args: String?) {
        val inputStream = this::class.java.getResourceAsStream("/stocks/stocks.csv")
            ?: throw IllegalArgumentException("CSV not found")

        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            val lines = reader.readLines()
                .filter { it.isNotBlank() && !it.startsWith(",") }
                .dropWhile { !it.startsWith("id") }
                .drop(1) // header 제외

            val stocks = lines.map { line ->
                val tokens = line.split(",")
                Stock(
                    id = tokens[0].toLong(),
                    code = tokens[1],
                    name = tokens[2],
                    price = tokens[3].toInt(),
                    priceYesterday = tokens[4].toInt(),
                    volume = tokens[5].toLong()
                )
            }
            stockRepository.saveAll(stocks)
            logger.debug("Loaded ${stocks.size} stocks into H2 DB")
        }
    }
}