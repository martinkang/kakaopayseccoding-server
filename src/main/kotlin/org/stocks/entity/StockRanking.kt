package org.stocks.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.stocks.enum.RankingTag

@Entity
@Table(
    name = "stock_ranking",
    indexes = [
        Index(name = "idx_tag_time", columnList = "tag,timeTag")
    ]
)
data class StockRanking(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val tag: RankingTag,  // POPULARITY, GAIN, LOSS, VOLUME
    @Column(nullable = false)
    val timeTag: String, // yyyy-MM-dd HH:mm 단위

    @Column(nullable = false)
    val stockCode: String,
    val price: Int,
    val volume: Long,
    var priceChange: Int = 0
)