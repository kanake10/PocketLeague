package com.adammcneilly.pocketleague.core.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.adammcneilly.pocketleague.shared.core.ui.UIImage

/**
 * A custom composable for an [Image] that will set the painter based on the [image] supplied.
 */
@Composable
fun PocketLeagueImage(
    image: UIImage,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    when (image) {
        is UIImage.AndroidResource -> {
            Image(
                painterResource(id = image.drawableRes),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
            )
        }
        is UIImage.Remote -> {
            Image(
                rememberImagePainter(data = image.imageUrl),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale,
            )
        }
    }
}
