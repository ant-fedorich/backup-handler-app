package com.antonfedorych.backuphandlerapp.data.local.mapper

import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus

fun BackupItemEntity.toBackupItem(): BackupItem =
    BackupItem(
        id = id,
        fileName = fileName,
        status = toBackupStatus(),
    )

fun List<BackupItemEntity>.toBackupItems(): List<BackupItem> = map { it.toBackupItem() }

private fun BackupItemEntity.toBackupStatus(): BackupStatus =
    when (status) {
        "Pending" -> BackupStatus.Pending
        "Loading" -> BackupStatus.Loading
        "Success" -> BackupStatus.Success
        "Failure" -> BackupStatus.Failure(failureReason ?: "Unknown error")
        "Interrupted" -> BackupStatus.Interrupted
        else -> throw IllegalArgumentException("Unknown backup status: $status")
    }
