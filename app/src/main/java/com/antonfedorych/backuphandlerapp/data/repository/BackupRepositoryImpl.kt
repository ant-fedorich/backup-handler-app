package com.antonfedorych.backuphandlerapp.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.repository.BackupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BackupRepositoryImpl(
    private val contentResolver: ContentResolver,
) : BackupRepository {

    private val _items = MutableStateFlow<List<BackupItem>>(emptyList())

    override fun observeItems(): Flow<List<BackupItem>> {
        _items.asStateFlow()
    }

    override suspend fun startBackup(uris: List<Uri>) {
    }
}
