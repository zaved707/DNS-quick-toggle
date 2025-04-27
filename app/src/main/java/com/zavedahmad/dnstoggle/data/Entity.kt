package com.zavedahmad.dnstoggle.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dns_entries")
data class DnsDomainEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val domain: String
)