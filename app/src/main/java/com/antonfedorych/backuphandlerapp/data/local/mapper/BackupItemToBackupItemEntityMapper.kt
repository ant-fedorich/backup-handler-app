package com.antonfedorych.backuphandlerapp.data.local.mapper

import com.antonfedorych.backuphandlerapp.data.local.entity.BackupItemEntity
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus

fun BackupItem.toBackupItemEntity(): BackupItemEntity =
    BackupItemEntity(
        id = id,
        fileName = fileName,
        status = status.toStatusString(),
    )

private fun BackupStatus.toStatusString(): String =
    when (this) {
        BackupStatus.Pending -> "Pending"
        BackupStatus.Loading -> "Loading"
        BackupStatus.Success -> "Success"
        BackupStatus.Failure -> "Failure"
    }
