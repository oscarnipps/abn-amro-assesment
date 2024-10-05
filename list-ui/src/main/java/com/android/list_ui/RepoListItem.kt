package com.android.list_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun RepoListItem(
    modifier: Modifier = Modifier,
    repo: Repo,
    onRepoItemClicked: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onRepoItemClicked(repo.id) },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RepoImage(imageUrl = repo.ownerImageUrl)

        Column(modifier = Modifier, Arrangement.Center) {
            RepoListItemText(text = repo.name)

            RepoListItemText(text = repo.visibility)

            RepoListItemText(text = repo.isPrivate)
        }
    }


}

//todo: refactor to a common module so as to make reusable across
@Composable
private fun RepoImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .error(LocalContext.current.getDrawable(R.drawable.default_repo_icon))
            .build()
    )

    Image(
        painter = painter,
        contentDescription = stringResource(R.string.content_description_repo_image),
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun RepoListItemText(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(
        modifier = modifier,
        text = text
    )

    Spacer(modifier = modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun RepoListItemPreview() {
    RepoListItem(
        repo = Repo(
            id = "",
            name = "ABN repo name",
            visibility = "ABN repo visibility",
            isPrivate = "ABN repo is private",
            ownerImageUrl = ""
        )
    )
}