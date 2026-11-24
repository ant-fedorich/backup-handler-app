package com.antonfedorych.backuphandlerapp.domain.model.enums

import androidx.compose.ui.graphics.Color

enum class BackupStatus(val color: Color) {
    Pending(Color(0xFF757575)),
    Loading(Color(0xFFF9A825)),
    Success(Color(0xFF2E7D32)),
    Failure(Color(0xFFC62828)),
    Interrupted(Color(0xFFC62828)),
}
