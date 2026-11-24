package com.antonfedorych.backuphandlerapp.data.repository

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus
import com.antonfedorych.backuphandlerapp.domain.repository.BackupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class BackupRepositoryImpl(
    context: Context,
) : BackupRepository {
    private val contentResolver = context.contentResolver
    private val backupDir = File(context.filesDir, "backups")

    private val _items = MutableStateFlow<List<BackupItem>>(emptyList())

    override fun observeItems(): Flow<List<BackupItem>> = _items.asStateFlow()

    override suspend fun startBackup(uris: List<Uri>) = withContext(Dispatchers.IO) {
        backupDir.mkdirs()

        _items.value = emptyList()

        uris.forEachIndexed { index, uri ->
            val name = getFileName(uri) ?: "file_$index"

            val status = try {
                if (name.contains("fail_")) throw IOException("Simulated failure") //demo

                copyFile(uri, File(backupDir, name))
                BackupStatus.Success
            } catch (e: Exception) {
                BackupStatus.Failure
            }
            _items.value += BackupItem(id = index.toLong(), fileName = name, status = status)
        }
    }

    private fun getFileName(uri: Uri): String? =
        contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.use { cursor -> if (cursor.moveToFirst()) cursor.getString(0) else null }

    private fun copyFile(source: Uri, destination: File) {
        val inputInput = contentResolver.openInputStream(source)
            ?: throw FileNotFoundException("Cannot open $source")

        inputInput.use { input ->
            destination.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }
}
