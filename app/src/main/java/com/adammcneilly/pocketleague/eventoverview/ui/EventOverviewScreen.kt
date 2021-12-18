package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Displays the [EventOverviewContent] with the view state pulled from the supplied [viewModel].
 */
@Destination(
    navArgsDelegate = EventOverviewNavArgs::class,
)
@Composable
fun EventOverviewScreen(
    modifier: Modifier = Modifier,
    viewModel: EventOverviewViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    EventOverviewContent(
        viewState = viewState.value,
        modifier = modifier,
    )
}
