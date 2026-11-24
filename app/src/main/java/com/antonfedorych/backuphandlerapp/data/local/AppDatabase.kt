package com.antonfedorych.backuphandlerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity

@Database(
    entities = [BackupItemEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun backupDao(): BackupDao
}
