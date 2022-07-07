package com.tomtom.game

import com.tomtom.game.CombatResult.*
import com.tomtom.game.FellowshipState.*

class Fellowship(
    private val members: List<Character>,
    private var state: FellowshipState = RESTED
) {

    fun combat(enemies: Fellowship): CombatResult {
        if (isReadyToFight()) {
            val fellowshipStrength = strength()
            val enemiesStrength = enemies.strength()

            return if (fellowshipStrength > enemiesStrength) {
                if ((0..100).random() < 10) state = NEEDS_REPAIR
                VICTORY
            } else if (fellowshipStrength == enemiesStrength) {
                DRAW
            } else {
                when (state) {
                    RESTED -> state = LOW_MORALE
                    LOW_MORALE -> state = EXHAUSTED
                    else -> state
                }
                DEFEAT
            }
        } else {
            throw RuntimeException("Fellowship wasn't ready to fight")
        }
    }

    fun rest() {
        state = buildCampfire(state)
    }

    fun repair() {
        state = useRepairTools(state)
    }

    private fun strength() = this.members.sumOf { it.level }
    private fun isReadyToFight(): Boolean =
        when (state) {
            RESTED -> true
            else -> false
        }
}

