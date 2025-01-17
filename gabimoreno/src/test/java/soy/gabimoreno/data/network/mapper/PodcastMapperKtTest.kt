package soy.gabimoreno.data.network.mapper

import com.prof.rssparser.*
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.util.*

class PodcastMapperKtTest {

    @Test
    fun `GIVEN a Channel WHEN toDomain THEN get the expected PodcastSearch`() {
        val channel = buildChannel()
        val numberOfEpisodes = channel.articles.size.toLong()

        val result = channel.toDomain()

        result.count shouldBe numberOfEpisodes
        result.total shouldBe numberOfEpisodes

        val episode = result.results[0]
        episode.id shouldBeEqualTo EPISODE_ID
        episode.url shouldBe EPISODE_URL
        episode.audioUrl shouldBe EPISODE_AUDIO_URL
        episode.imageUrl shouldBe EPISODE_IMAGE_URL
        episode.thumbnailUrl shouldBe EPISODE_IMAGE_URL
        episode.pubDateMillis shouldBeEqualTo Date(EPISODE_PUB_DATE).time
        episode.title shouldBe EPISODE_TITLE
        episode.audioLengthSeconds shouldBeEqualTo EPISODE_AUDIO_LENGTH_IN_SECONDS
        episode.description shouldBeEqualTo EPISODE_DESCRIPTION

        val podcast = episode.podcast
        podcast.title shouldBe PODCAST_TITLE
        podcast.author shouldBeEqualTo PODCAST_TITLE.uppercase()
    }

    private fun buildChannel(): Channel {
        return Channel(
            PODCAST_TITLE,
            "link",
            "description",
            Image(
                "title",
                "url",
                "link",
                "description"
            ),
            "lastBuildDate",
            "updatePeriod",
            listOf(
                Article(
                    "$EPISODE_ID$IVOOX_URL",
                    EPISODE_TITLE,
                    "author",
                    "link",
                    EPISODE_PUB_DATE,
                    "$EPISODE_DESCRIPTION$ANCHOR_MESSAGE",
                    "content",
                    null,
                    EPISODE_AUDIO_URL,
                    "video",
                    "sourceName",
                    "sourceUrl",
                    listOf("categories"),
                    ItunesArticleData(
                        "author",
                        EPISODE_AUDIO_LENGTH_IN_SECONDS.toString(),
                        "episode",
                        "episodeType",
                        "explicit",
                        EPISODE_IMAGE_URL,
                        listOf("keywords"),
                        "subtitle",
                        "summary"
                    )
                )
            ),
            ItunesChannelData(
                "author",
                listOf("categories"),
                "2000",
                "explicit",
                "channelImage",
                listOf("keywords"),
                "newsFeedUrl",
                ItunesOwner(
                    "name",
                    "email"
                ),
                "subtitle",
                "summary",
                "type"
            )
        )
    }
}

private const val EPISODE_ID = "guid"
private const val EPISODE_URL = "https://gabimoreno.soy"
private const val EPISODE_AUDIO_URL = "audio"
private const val EPISODE_IMAGE_URL = "image"
private const val EPISODE_TITLE = "title"
private const val EPISODE_AUDIO_LENGTH_IN_SECONDS = 896
private const val EPISODE_PUB_DATE = "Mon, 27 Jun 2022 04:00:33 GMT"
private const val EPISODE_DESCRIPTION = "description"

private const val PODCAST_TITLE = "podcastTitle"
