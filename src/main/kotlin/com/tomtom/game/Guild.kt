package com.tomtom.game

import com.tomtom.game.CombatResult.*
import com.tomtom.game.TaskResult.*

class Guild(private val members: Fellowship) {
    fun completeTask(enemies: Fellowship): TaskResult {
        return when (members.combat(enemies)) {
            VICTORY -> SUCCESS
            else -> FAILED
        }
    }
}