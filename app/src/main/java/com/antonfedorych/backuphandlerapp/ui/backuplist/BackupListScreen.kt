package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem
import com.antonfedorych.backuphandlerapp.domain.model.enums.BackupStatus
import com.antonfedorych.backuphandlerapp.ui.common.theme.BackupHandlerAppTheme

@Composable
fun BackupListScreen(
    viewModel: BackupListViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pickFilesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onEvent(BackupListEvent.FilesSelected(uris))
        }
    }

    BackupListContent(
        state = state,
        onEvent = viewModel::onEvent,
        onSelectFilesClick = { pickFilesLauncher.launch(arrayOf("*/*")) },
        modifier = modifier,
    )
}

@Composable
fun BackupListContent(
    state: BackupListState,
    onEvent: (BackupListEvent) -> Unit,
    onSelectFilesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(vertical = 42.dp)
            .padding(horizontal = 8.dp)
    ) {

        LazyColumn {
            items(state.items, key = { it.id }) { item ->
                ListItem(
                    headlineContent = { Text(item.fileName) },
                    trailingContent = {
                        Text(text = item.status.name, color = item.status.color)
                    },
                )
                HorizontalDivider()
            }
        }

        Button(
            onClick = onSelectFilesClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text("Select files")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackupListContentPreview() {
    BackupHandlerAppTheme {
        BackupListContent(
            state = BackupListState(items = sampleBackupItems),
            onEvent = {},
            onSelectFilesClick = {},
        )
    }
}

val sampleBackupItems = listOf(
    BackupItem(id = 1, fileName = "photo.jpg", status = BackupStatus.Success),
    BackupItem(id = 2, fileName = "report.pdf", status = BackupStatus.Failure),
    BackupItem(id = 3, fileName = "notes.txt", status = BackupStatus.Loading),
    BackupItem(id = 4, fileName = "video.mp4", status = BackupStatus.Pending),
)
