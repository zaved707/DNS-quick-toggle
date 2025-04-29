package com.zavedahmad.dnstoggle.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface DnsDomainEntryDao {
    @Insert
    fun insert(entry: DnsDomainEntry)

    @Query("SELECT * FROM dns_entries")
    fun getAllEntries(): Flow<List<DnsDomainEntry>>

    @Query("DELETE FROM dns_entries")
    fun deleteAll()

    @Query("SELECT * FROM dns_entries WHERE isActive = 1 LIMIT 1")
    suspend fun getActiveEntry(): DnsDomainEntry?

    // Transaction to activate one entry and deactivate others
    @Transaction
    suspend fun setActiveEntry(entryId: Int) {
        // Deactivate all entries
        deactivateAll()
        // Activate the selected entry
        activateEntry(entryId)
    }

    @Query("UPDATE dns_entries SET isActive = 0")
    suspend fun deactivateAll()

    @Query("UPDATE dns_entries SET isActive = 1 WHERE id = :entryId")
    suspend fun activateEntry(entryId: Int)

    @Query("DELETE FROM dns_entries WHERE id = :entryId")
    suspend fun deleteEntry(entryId: Int)
}