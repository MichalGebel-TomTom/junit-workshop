package com.tomtom.game

import com.tomtom.game.FellowshipState.*

fun buildCampfire(state: FellowshipState) =
    when (state) {
        EXHAUSTED -> RESTED
        LOW_MORALE -> EXHAUSTED
        else -> state
    }