package com.antonfedorych.backuphandlerapp.data.repository.backup

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.antonfedorych.backuphandlerapp.data.local.BackupDao
import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity
import com.antonfedorych.backuphandlerapp.data.local.mapper.toBackupItems
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus
import com.antonfedorych.backuphandlerapp.domain.repository.BackupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.time.Duration.Companion.seconds

class BackupRepositoryImpl(
    context: Context,
    private val dao: BackupDao,
) : BackupRepository {
    private val contentResolver = context.contentResolver
    private val backupDir = File(context.filesDir, "backups")

    override fun observeItems(): Flow<List<BackupItem>> =
        dao.observeAll().map { it.toBackupItems() }

    override suspend fun startBackup(uris: List<Uri>) = withContext(Dispatchers.IO) {
        backupDir.mkdirs()

        dao.deleteAll()

        val items = uris.mapIndexed { index, uri ->
            BackupItemEntity(
                id = index,
                fileName = getFileName(uri) ?: "file_$index",
                status = BackupStatus.Pending.name,
            )
        }
        dao.insertAll(items)

        items.forEachIndexed { index, item ->
            val uri = uris[index]
            val name = item.fileName
            dao.insert(item.copy(status = BackupStatus.Loading.name))

            val status = try {
                delay(1.seconds) //demo
                if (name.contains("fail_")) throw IOException("Simulated failure") //demo

                copyFile(
                    uri,
                    File(backupDir, "${index}_$name")
                )
                BackupStatus.Success
            } catch (e: Exception) {
                BackupStatus.Failure
            }
            dao.insert(item.copy(status = status.name))
        }
    }

    override suspend fun markInterruptedBackup() = dao.markLoadingAsInterrupted()

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