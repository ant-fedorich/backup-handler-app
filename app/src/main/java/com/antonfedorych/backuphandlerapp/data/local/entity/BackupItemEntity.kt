package com.antonfedorych.backuphandlerapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "backup_items")
data class BackupItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "file_name")
    val fileName: String,
    val status: String,
)
