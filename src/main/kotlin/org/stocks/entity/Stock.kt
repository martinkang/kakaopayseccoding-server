package org.stocks.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    name = "stock",
    indexes = [
        Index(name = "idx_volume", columnList = "volume"),
        Index(name = "idx_gain_rate", columnList = "gainRate")
    ]
)

data class Stock(
    @Id
    val id: Long,
    val code: String,
    val name: String,
    val price: Int,
    val priceYesterday: Int,
    val volume: Long,
    val gainRate: Double // (price - priceYesterday) / priceYesterday 미리 계산
)