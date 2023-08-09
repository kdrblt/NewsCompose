package com.example.newscompose.ui.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.newscompose.R

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomDateRangePickerDialog(
    showDialog: Boolean,
    onSelected: (Long, Long) -> Unit,
    onDialogDismiss: (Boolean) -> Unit

) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDialogDismiss(true)
            }
        ) {
            Surface(
                shape = RoundedCornerShape(
                    size = 10.dp
                )
            ) {
                DateRangePicker(
                    onDialogDismiss = {
                        onDialogDismiss(false)
                    },
                    onDateSelected = { start, end ->
                        onSelected(start, end)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePicker(
    onDialogDismiss: (Boolean) -> Unit,
    onDateSelected: (Long, Long) -> Unit
) {
    val state = rememberDateRangePickerState()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        // Add a row with "Save" and dismiss actions.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                onDialogDismiss(true)
            }) {
                Icon(Icons.Filled.Close, contentDescription = null)
            }
            TextButton(
                onClick = {
                    state.selectedEndDateMillis?.let { end ->
                        state.selectedStartDateMillis?.let { start ->
                            onDateSelected(
                                start,
                                end
                            )
                        }
                    }
                },
                enabled = state.selectedEndDateMillis != null
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }

        DateRangePicker(state = state, modifier = Modifier.weight(1f))
    }
}
