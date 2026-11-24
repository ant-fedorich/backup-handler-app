package com.antonfedorych.backuphandlerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BackupDao {
    @Query("SELECT * FROM backup_items ORDER BY id")
    fun observeAll(): Flow<List<BackupItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BackupItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<BackupItemEntity>)

    @Query("UPDATE backup_items SET status = 'Interrupted' WHERE status IN ('Pending', 'Loading')")
    suspend fun markLoadingAsInterrupted()

    @Query("DELETE FROM backup_items")
    suspend fun deleteAll()
}
