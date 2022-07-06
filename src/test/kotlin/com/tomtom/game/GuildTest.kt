package com.tomtom.game

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GuildTest {
    @ParameterizedTest(name = "when fellowship combat result is {0} then task result should be {1}")
    @CsvSource(
        delimiterString = "->",
        textBlock = """
        VICTORY -> SUCCESS
        DEFEAT -> FAILED
        DRAW -> FAILED"""
    )
    fun `task conditions`(combatResult: CombatResult, expectedResult: TaskResult) {
        val members = mockk<Fellowship>()
        val enemies = mockk<Fellowship>()
        every { members.combat(enemies) } returns combatResult
        val guild = Guild(members)

        val result = guild.completeTask(enemies)

        assertEquals(expectedResult, result)
    }
}