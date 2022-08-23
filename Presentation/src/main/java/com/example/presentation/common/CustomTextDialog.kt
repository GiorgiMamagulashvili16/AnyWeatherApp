package com.example.presentation.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomTextDialog(
    onDismiss: (Boolean) -> Unit,
    positiveButtonText: String = "OK",
    negativeButtonText: String = "Cancel",
    messageText: String,
    onButtonClick: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss.invoke(false) },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {

            Column(modifier = Modifier.padding(21.dp)) {
                Text(text = messageText, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Row {
                    TextButton(
                        onClick = { onButtonClick.invoke(false) },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = negativeButtonText, textAlign = TextAlign.Center)
                    }
                    TextButton(
                        onClick = { onButtonClick.invoke(false) },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text(text = positiveButtonText, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}