package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.shared.screens.eventdetail.initEventDetail
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.initEventStageDetail
import com.adammcneilly.pocketleague.shared.screens.feed.initFeed
import com.adammcneilly.pocketleague.shared.screens.matchdetail.initMatchDetail
import com.adammcneilly.pocketleague.shared.screens.myteams.initMyTeams
import com.adammcneilly.pocketleague.shared.screens.records.initRecords
import com.adammcneilly.pocketleague.shared.screens.stats.initStats
import com.adammcneilly.pocketleague.shared.screens.teamselection.initTeamSelection

/**
 * An enumeration of all screens that appear somewhere in our application.
 */
enum class Screens(
    val asString: String,
    val navigationLevel: Int = 1,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings,
    val stackableInstances: Boolean = false,
) {
    Feed(
        asString = "feed",
        navigationLevel = 1,
        initSettings = {
            initFeed()
        },
        stackableInstances = true,
    ),
    MyTeams(
        asString = "my_teams",
        navigationLevel = 1,
        initSettings = {
            initMyTeams()
        },
        stackableInstances = true,
    ),
    Stats(
        asString = "stats",
        navigationLevel = 1,
        initSettings = {
            initStats()
        },
        stackableInstances = true,
    ),
    Records(
        asString = "records",
        navigationLevel = 1,
        initSettings = {
            initRecords()
        },
        stackableInstances = true,
    ),
    MatchDetail(
        asString = "match_detail",
        navigationLevel = 2,
        initSettings = {
            initMatchDetail(it.params())
        },
        stackableInstances = false,
    ),
    EventDetail(
        asString = "event_detail",
        navigationLevel = 2,
        initSettings = {
            initEventDetail(it.params())
        },
        stackableInstances = false,
    ),
    EventStageDetail(
        asString = "event_stage_detail",
        navigationLevel = 3,
        initSettings = {
            initEventStageDetail(it.params())
        },
        stackableInstances = false,
    ),
    TeamSelection(
        asString = "team_selection",
        navigationLevel = 2,
        initSettings = {
            initTeamSelection()
        },
        stackableInstances = false,
    ),
}
