package com.antonfedorych.backuphandlerapp.domain.model

import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus

data class BackupItem(
    val id: Long,
    val fileName: String,
    val status: BackupStatus,
)
