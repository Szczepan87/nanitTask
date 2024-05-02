package com.example.nanittask.birthday

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nanittask.R
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun ChildImageComponent(
    modifier: Modifier = Modifier,
    imagePlaceholder: @Composable () -> Unit,
    cameraIcon: @Composable () -> Unit,
    loadedImage: @Composable () -> Unit = {}
) {
    Layout(
        contents = listOf(imagePlaceholder, cameraIcon, loadedImage),
        modifier = modifier
    ) { (imagePlaceholderMesurable, cameraIconMesurable, loadedImageMesurable), constraints ->
        val imagePlaceholderPlaceable = imagePlaceholderMesurable.first().measure(constraints)
        val loadedImagePlaceable = loadedImageMesurable.firstOrNull()?.measure(
            constraints.copy(
                maxWidth = imagePlaceholderPlaceable.width - MARGIN_OFFSET,
                maxHeight = imagePlaceholderPlaceable.height - MARGIN_OFFSET
            )
        )
        val cameraIconPlaceable = cameraIconMesurable.first().measure(constraints)

        layout(imagePlaceholderPlaceable.width, imagePlaceholderPlaceable.height) {
            imagePlaceholderPlaceable.place(0, 0)
            loadedImagePlaceable?.place(MARGIN_OFFSET.div(2), MARGIN_OFFSET.div(2))
            cameraIconPlaceable.place(
                x = imagePlaceholderPlaceable.width - (imagePlaceholderPlaceable.width.toFloat()
                    .div(2f)).times(cos(DEGREES_45)).roundToInt(),
                y = (cameraIconPlaceable.height.toFloat() / 2f).times(sin(DEGREES_45)).roundToInt()
            )
        }
    }
}

@Preview
@Composable
private fun ChildImageComponentPreview() {
    ChildImageComponent(
        imagePlaceholder = {
            Image(
                painterResource(id = R.drawable.kid_round_elephant),
                contentDescription = stringResource(R.string.birthday_image_placeholder_content_description)
            )
        },
        cameraIcon = {
            Image(painterResource(id = R.drawable.camera_elephant), contentDescription = null)
        },
        loadedImage = {
            Image(
                painterResource(id = R.drawable.kid_round_pelican),
                contentDescription = stringResource(R.string.birthday_image_placeholder_content_description)
            )
        }
    )
}

const val MARGIN_OFFSET = 32
const val DEGREES_45 = 45f