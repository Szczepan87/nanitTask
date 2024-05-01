package com.example.nanittask.birthday

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nanittask.R
import com.example.nanittask.ui.theme.ElephantBackground
import com.example.nanittask.ui.theme.FoxBackground
import com.example.nanittask.ui.theme.PelicanBackground
import com.example.nanittaskdomain.ColorTheme

@Composable
fun BirthdayScreen(viewModel: BirthdayScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    BirthdayComponent(screenState = state.value)
}

@Composable
fun BirthdayComponent(screenState: BirthdayScreenState) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = decideBackgroundColor(screenState.theme)),
    ) {
        val context = LocalContext.current
        ConstraintLayout {
            val (nameText, sumupText, ageItem, leftSwirl, rightSwirl, babyPlaceholder, nanitLogo, backgroundImage) = createRefs()
            Text(
                text = stringResource(R.string.birthday_header_title, screenState.name),
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Image(
                painter = painterResource(id = decideAgePainterResource(screenState.age.age)),
                contentDescription = stringResource(R.string.birthday_age_value_content_description),
                modifier = Modifier.constrainAs(ageItem) {
                    top.linkTo(nameText.bottom, margin = 13.dp)
                    start.linkTo(nameText.start)
                    end.linkTo(nameText.end)
                })
            Image(
                painter = painterResource(id = R.drawable.left_swirls),
                contentDescription = stringResource(R.string.birthday_age_left_decoarator_content_description),
                modifier = Modifier.constrainAs(leftSwirl) {
                    top.linkTo(ageItem.top)
                    bottom.linkTo(ageItem.bottom)
                    end.linkTo(ageItem.start, margin = 22.dp)
                })
            Image(
                painterResource(id = R.drawable.right_swirls),
                contentDescription = stringResource(R.string.birthday_age_right_decorator_content_description),
                modifier = Modifier.constrainAs(rightSwirl) {
                    top.linkTo(ageItem.top)
                    bottom.linkTo(ageItem.bottom)
                    start.linkTo(ageItem.end, margin = 22.dp)
                })
            Text(
                text = decideAgeSumupText(screenState.age, context),
                modifier = Modifier.constrainAs(sumupText) {
                    top.linkTo(ageItem.bottom, margin = 14.dp)
                    start.linkTo(nameText.start)
                    end.linkTo(nameText.end)
                })
            Image(
                painterResource(id = decideKidPainterResource(screenState.theme)),
                contentDescription = stringResource(R.string.birthday_image_placeholder_content_description),
                modifier = Modifier.constrainAs(babyPlaceholder) {
                    top.linkTo(sumupText.bottom, margin = 15.dp)
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end, margin = 50.dp)
                })
            Image(
                painterResource(id = R.drawable.nanit_logo),
                contentDescription = stringResource(R.string.birthday_nanit_logo_content_description),
                modifier = Modifier.constrainAs(nanitLogo) {
                    top.linkTo(babyPlaceholder.bottom, margin = 15.dp)
                    start.linkTo(babyPlaceholder.start)
                    end.linkTo(babyPlaceholder.end)
                })
            Image(
                painter = painterResource(id = decideBackgroundPainterResource(screenState.theme)),
                contentDescription = stringResource(R.string.birthday_background_content_description),
                modifier = Modifier.constrainAs(backgroundImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}

fun decideAgeSumupText(age: Age, context: Context): String {
    return if (age.isInMonths) {
        if (age.age == 1) {
            context.getString(R.string.birthday_month_singular_subtitle)
        } else {
            context.getString(R.string.birthday_month_plural_subtitle)
        }
    } else {
        if (age.age == 1) {
            context.getString(R.string.birthday_year_singular_subtitle)
        } else {
            context.getString(R.string.birthday_year_plural_subtitle)
        }
    }
}

@Preview
@Composable
private fun BirthdayScreenPreview() {
    BirthdayComponent(screenState = BirthdayScreenState(theme = ColorTheme.PELICAN))
}

private fun decideKidPainterResource(theme: ColorTheme): Int {
    return when (theme) {
        ColorTheme.ELEPHANT -> R.drawable.kid_round_elephant
        ColorTheme.FOX -> R.drawable.kid_round_fox
        ColorTheme.PELICAN -> R.drawable.kid_round_pelican
    }
}

private fun decideAgePainterResource(age: Int): Int {
    return when (age) {
        1 -> R.drawable.one
        2 -> R.drawable.two
        3 -> R.drawable.three
        4 -> R.drawable.four
        5 -> R.drawable.five
        6 -> R.drawable.six
        7 -> R.drawable.seven
        8 -> R.drawable.eight
        9 -> R.drawable.nine
        10 -> R.drawable.ten
        11 -> R.drawable.eleven
        12 -> R.drawable.twelve
        else -> R.drawable.one
    }
}

private fun decideBackgroundPainterResource(theme: ColorTheme): Int {
    return when (theme) {
        ColorTheme.ELEPHANT -> R.mipmap.bg_android_elephant
        ColorTheme.FOX -> R.mipmap.bg_android_fox
        ColorTheme.PELICAN -> R.mipmap.bg_android_pelican
    }
}

private fun decideBackgroundColor(colorTheme: ColorTheme) =
    when (colorTheme) {
        ColorTheme.ELEPHANT -> {
            ElephantBackground
        }

        ColorTheme.FOX -> {
            FoxBackground
        }

        ColorTheme.PELICAN -> {
            PelicanBackground
        }
    }
