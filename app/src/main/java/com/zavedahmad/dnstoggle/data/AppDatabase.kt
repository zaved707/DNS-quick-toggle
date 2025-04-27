package com.zavedahmad.dnstoggle.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities =[DnsDomainEntry::class], version=1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dnsDomainEntryDao(): DnsDomainEntryDao
}