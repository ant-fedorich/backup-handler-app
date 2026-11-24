package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus
import com.antonfedorych.backuphandlerapp.ui.common.theme.BackupHandlerAppTheme

@Composable
fun BackupListScreen(
    items: List<BackupItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(items, key = { it.id }) { item ->
            ListItem(
                headlineContent = { Text(item.fileName) },
                trailingContent = { Text(item.status.name) },
            )
            HorizontalDivider()
        }
    }
}

val sampleBackupItems = listOf(
    BackupItem(id = 1, fileName = "photo.jpg", status = BackupStatus.Success),
    BackupItem(id = 2, fileName = "report.pdf", status = BackupStatus.Failure),
    BackupItem(id = 3, fileName = "notes.txt", status = BackupStatus.Loading),
    BackupItem(id = 4, fileName = "video.mp4", status = BackupStatus.Pending),
)

@Preview(showBackground = true)
@Composable
private fun BackupListScreenPreview() {
    BackupHandlerAppTheme {
        BackupListScreen(items = sampleBackupItems)
    }
}
