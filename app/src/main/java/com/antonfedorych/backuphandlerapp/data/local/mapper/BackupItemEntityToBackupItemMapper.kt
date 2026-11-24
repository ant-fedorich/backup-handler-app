package com.antonfedorych.backuphandlerapp.data.local.mapper

import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus

fun BackupItemEntity.toBackupItem(): BackupItem =
    BackupItem(
        id = id,
        fileName = fileName,
        status = status.toBackupStatus(),
    )

fun List<BackupItemEntity>.toBackupItems(): List<BackupItem> = map { it.toBackupItem() }

private fun String.toBackupStatus(): BackupStatus =
    when (this) {
        "Pending" -> BackupStatus.Pending
        "Loading" -> BackupStatus.Loading
        "Success" -> BackupStatus.Success
        "Failure" -> BackupStatus.Failure
        else -> throw IllegalArgumentException("Unknown backup status: $this")
    }
