package com.tomtom.game

import com.tomtom.game.FellowshipState.*

fun useRepairTools(state: FellowshipState) = when (state) {
    NEEDS_REPAIR -> RESTED
    else -> state
}