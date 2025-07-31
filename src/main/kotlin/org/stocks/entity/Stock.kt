package org.stocks.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table

@Entity
@Table(
    name = "stock",
    indexes = [
        Index(name = "idx_volume", columnList = "volume"),
        Index(name = "idx_price_change", columnList = "priceChange")
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
    var priceChange: Int = 0 // (price - priceYesterday)
) {
    @PrePersist
    @PreUpdate
    fun calculateChanges() {
        this.priceChange = price - priceYesterday
    }
}