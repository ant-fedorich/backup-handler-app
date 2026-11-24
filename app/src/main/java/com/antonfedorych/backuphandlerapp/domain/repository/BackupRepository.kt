package com.antonfedorych.backuphandlerapp.domain.repository

import android.net.Uri
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import kotlinx.coroutines.flow.Flow

interface BackupRepository {
    fun observeItems(): Flow<List<BackupItem>>

    suspend fun startBackup(uris: List<Uri>)

    suspend fun markInterruptedBackup()
}
