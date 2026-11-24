package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus
import com.antonfedorych.backuphandlerapp.ui.common.theme.BackupHandlerAppTheme

@Composable
fun BackupListScreen(
    modifier: Modifier = Modifier,
    viewModel: BackupListViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    BackupListContent(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier,
    )
}

@Composable
fun BackupListContent(
    state: BackupListUiState,
    onEvent: (BackupListUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(state.items, key = { it.id }) { item ->
            ListItem(
                headlineContent = { Text(item.fileName) },
                trailingContent = { Text(item.status.name) },
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackupListContentPreview() {
    BackupHandlerAppTheme {
        BackupListContent(
            state = BackupListUiState(items = sampleBackupItems),
            onEvent = {},
        )
    }
}

val sampleBackupItems = listOf(
    BackupItem(id = 1, fileName = "photo.jpg", status = BackupStatus.Success),
    BackupItem(id = 2, fileName = "report.pdf", status = BackupStatus.Failure),
    BackupItem(id = 3, fileName = "notes.txt", status = BackupStatus.Loading),
    BackupItem(id = 4, fileName = "video.mp4", status = BackupStatus.Pending),
)
