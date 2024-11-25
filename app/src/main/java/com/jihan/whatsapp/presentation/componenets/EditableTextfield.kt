package com.jihan.whatsapp.presentation.componenets


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jihan.whatsapp.R


@Composable
fun EditableTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10)),
    value: String,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    padding: Dp = 0.dp,
    isPasswordFieldType: Boolean = false,
    onValueChange: (String) -> Unit,
) {


    var mVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier.padding(padding),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = { Text(text = label, color = MaterialTheme.colorScheme.onSurface) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color(0x25FFFFFE),
            unfocusedContainerColor = Color(0x4BFFFFFF),
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        leadingIcon = {
            if (leadingIcon != null) {
                leadingIcon()
            } else {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = null
                )
            }
        },
        trailingIcon = {

            if (isPasswordFieldType) {

                val icon = if (mVisible) {
                    painterResource(R.drawable.outline_visibility_24)
                } else {
                    painterResource(R.drawable.outline_visibility_off_24)
                }

                IconButton(onClick = {
                    mVisible = mVisible.not()
                }) {
                    Icon(painter = icon, contentDescription = null)
                }


            }


        },

        visualTransformation = if (isPasswordFieldType && !mVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    )
}

