package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun authenticationScreen(onLogin: (code: String) -> Unit) {
    var filled by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        codeTextFields {
            filled = true
            code = it
        }

        Button(
            modifier = Modifier.align(Alignment.End).padding(16.dp),
            enabled = filled,
            onClick = {
                onLogin(code)
            }) {
            Text("Log In")
        }
    }
}

@Composable
fun codeTextFields(
    modifier: Modifier = Modifier,
    length: Int = 4,
    onFilled: (code: String) -> Unit
) {
    var code: List<Char> by remember {
        mutableStateOf(listOf())
    }
    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) {
            temp.add(FocusRequester())
        }
        temp
    }

    Row(modifier = modifier) {
        (0 until length).forEach { index ->
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 2.dp)
                    .focusRequester(focusRequesters[index]),
                textStyle = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
                singleLine = true,
                value = code.getOrNull(index)?.takeIf { it.isDigit() }?.toString() ?: "",
                onValueChange = { value: String ->
                    if (focusRequesters[index].freeFocus()) {   //For some reason this fixes the issue of focusrequestor causing on value changed to call twice
                        val temp = code.toMutableList()
                        if (value == "") {
                            if (temp.size > index) {
                                temp.removeAt(index)
                                code = temp
                                focusRequesters.getOrNull(index - 1)?.requestFocus()
                            }
                        } else {
                            if (code.size > index) {
                                temp[index] = value.getOrNull(0) ?: ' '
                            } else if (value.getOrNull(0)?.isDigit() == true) {
                                temp.add(value.getOrNull(0) ?: ' ')
                                code = temp
                                focusRequesters.getOrNull(index + 1)?.requestFocus() ?: onFilled(
                                    code.joinToString(separator = "")
                                )
                            }
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),

                )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}