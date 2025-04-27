package com.zavedahmad.dnstoggle.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DnsDomainEntryDao {
    @Insert
    fun insert(entry: DnsDomainEntry)

    @Query("SELECT * FROM dns_entries")
    fun getAllEntries():List<DnsDomainEntry>

    @Query("DELETE FROM dns_entries")
    fun deleteAll()
}