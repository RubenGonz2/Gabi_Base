package soy.gabimoreno.presentation.screen.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import soy.gabimoreno.R
import soy.gabimoreno.data.tracker.Tracker
import soy.gabimoreno.data.tracker.domain.PlayPause
import soy.gabimoreno.data.tracker.main.DetailTrackerEvent
import soy.gabimoreno.data.tracker.toMap
import soy.gabimoreno.domain.model.Episode
import soy.gabimoreno.framework.intent.StartActionView
import soy.gabimoreno.framework.intent.StartChooser
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val tracker: Tracker,
    private val startChooser: StartChooser,
    private val startActionView: StartActionView
) : ViewModel() {

    fun onViewScreen(episode: Episode) {
        tracker.trackEvent(DetailTrackerEvent.ViewScreen(episode.toMap()))
    }

    fun onPlayPauseClicked(
        episode: Episode,
        playPause: PlayPause
    ) {
        val parameters = episode.toMap()
        when (playPause) {
            PlayPause.PLAY -> tracker.trackEvent(DetailTrackerEvent.ClickPlay(parameters))
            PlayPause.PAUSE -> tracker.trackEvent(DetailTrackerEvent.ClickPause(parameters))
        }
    }

    fun onShareClicked(
        context: Context,
        episode: Episode
    ) {
        tracker.trackEvent(DetailTrackerEvent.ClickShare(episode.toMap()))
        startChooser(
            context,
            chooserTitleResId = R.string.share_podcast_content,
            title = episode.title,
            url = episode.url
        )
    }

    fun onInfoClicked(
        context: Context,
        episode: Episode
    ) {
        tracker.trackEvent(DetailTrackerEvent.ClickInfo(episode.toMap()))
        startActionView(context, episode.url)
    }
}
