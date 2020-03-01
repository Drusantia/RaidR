package net.drusantia.raidr.data.model.character

enum class Fields {
    GENERAL {
        override fun getUrlParams() =
            /* Retrieve high level item information for player */
            "gear," +
            /* Retrieve basic information about guild the player is in */
            "guild"
    },
    RAIDING {
        override fun getUrlParams() =
            /* Retrieve raid progression data for character */
            "raid_progression"
    },
    MYTHIC_PLUS {
        override fun getUrlParams() =
            /* Retrieve scores by mythic plus season.
            You can specify one or more season by appending multiple ':' values to this field.
            You can also use the alias 'current' and 'previous' instead of a season name to request that relative season.
            For example: mythic_plus_scores_by_season:current will request the current season.
            Note: Results are returned in an array that matches the order of the seasons in the request */
            "mythic_plus_scores_by_season," +
            /* Retrieve current season mythic plus rankings for player */
            "mythic_plus_ranks," +
            /* Retrieve three most recent mythic plus runs for player (current season only) */
            "mythic_plus_recent_runs," +
            /* Retrieve three most high scoring mythic plus runs for player (current season only).
            Specify the parameter :all to retrieve all of a character's best runs for the season */
            "mythic_plus_best_runs:all," +
            /* Retrieve the player's three highest Mythic+ runs by Mythic+ level (current season only) */
            "mythic_plus_highest_level_runs," +
            /* Retrieve the player's three highest Mythic+ runs by Mythic+ level for the current raid week (current season only) */
            "mythic_plus_weekly_highest_level_runs," +
            /* Retrieve the player's three highest Mythic+ runs by Mythic+ level for the previous raid week (current season only) */
            "mythic_plus_previous_weekly_highest_level_runs," +
            /* Retrieve mythic plus rankings for player */
            "previous_mythic_plus_ranks"
    },
    OTHER {
        override fun getUrlParams() = ""
            /* Retrieve raid achievement meta status for a player.
            This request requires that you specify parameters for the specific tiers you're looking for.
            For example if you add ':tier21' to the field you will get the status of Tier 21's meta. Multiple tiers can be added to a single request: ':tier21:tier20:tier19'*/
            //"raid_achievement_meta," +
            /* Retrieve Ahead of the Curve/Cutting Edge achievement status for a given raid slug (or multiple).
            Multiple slugs can be specified by separating them by colons. */
            //"raid_achievement_curve"
    },
    ALL {
        override fun getUrlParams() = listOf(
            GENERAL.getUrlParams(),
            RAIDING.getUrlParams(),
            MYTHIC_PLUS.getUrlParams(),
            OTHER.getUrlParams()
        ).joinToString(separator = ",")
        .trimEnd(',')
    };

    abstract fun getUrlParams(): String
}