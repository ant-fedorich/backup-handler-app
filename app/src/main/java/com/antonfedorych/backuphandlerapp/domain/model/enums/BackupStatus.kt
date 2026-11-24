package com.antonfedorych.backuphandlerapp.domain.model.enums

import androidx.compose.ui.graphics.Color

sealed class BackupStatus(val name: String, val color: Color) {
    data object Pending : BackupStatus("Pending", Color(0xFF757575))
    data object Loading : BackupStatus("Loading", Color(0xFFF9A825))
    data object Success : BackupStatus("Success", Color(0xFF2E7D32))
    data class Failure(val reason: String) : BackupStatus("Failure", Color(0xFFC62828))
    data object Interrupted : BackupStatus("Interrupted", Color(0xFFC62828))
}
