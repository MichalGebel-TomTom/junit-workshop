package com.tomtom.game

import com.tomtom.game.CombatResult.*
import com.tomtom.game.FellowshipState.RESTED
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE
import org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE

internal class FellowshipTest {

    @Test
    fun `when fellowship is stronger than enemies then should return VICTORY`() {
        val heroes = fellowship(listOf(hero(level = 10), hero(level = 8)))
        val enemies = fellowship(listOf(enemy(level = 9), enemy(level = 8)))

        assertEquals(VICTORY, heroes.combat(enemies))
    }

    @Test
    fun `when fellowship is equally strong to their enemies then should return DRAW`() {
        val heroes = fellowship(listOf(hero(level = 10), hero(level = 8)))
        val enemies = fellowship(listOf(enemy(level = 10), enemy(level = 8)))

        assertEquals(DRAW, heroes.combat(enemies))
    }

    @Test
    fun `when fellowship is weaker than their enemies then should return DEFEAT`() {
        val heroes = fellowship(listOf(hero(level = 9), hero(level = 8)))
        val enemies = fellowship(listOf(enemy(level = 10), enemy(level = 8)))

        assertEquals(DEFEAT, heroes.combat(enemies))
    }

    @ParameterizedTest(name = "when {0} lvl fellowship combats {1} lvl fellowship then result should be {2}")
    @CsvSource(
        textBlock = """
        10, 9, VICTORY
        9, 10, DEFEAT
        9, 9, DRAW"""
    )
//    @CsvSource(
//        "10, 9, VICTORY",
//        "9, 10, DEFEAT",
//        "9, 9, DRAW"
//    )
    fun `combat result conditions`(heroLvl: Int, enemyLvl: Int, expectedResult: CombatResult) {
        val heroes = fellowship(listOf(hero(level = heroLvl)))
        val enemies = fellowship(listOf(enemy(level = enemyLvl)))

        assertEquals(expectedResult, heroes.combat(enemies))
    }

    @ParameterizedTest
    @EnumSource(FellowshipState::class, names = ["RESTED"], mode = INCLUDE)
    fun `when fellowship is rested then combat should start`(state: FellowshipState) {
        val heroes = fellowship(listOf(hero(level = 3)), state)
        val enemies = fellowship(listOf(enemy(level = 0)))

        val result = heroes.combat(enemies)

        assertEquals(true, CombatResult.values().contains(result))
    }

    @ParameterizedTest
    @EnumSource(FellowshipState::class, names = ["RESTED"], mode = EXCLUDE)
    fun `when fellowship is not rested then exception should be thrown`(state: FellowshipState) {
        val heroes = fellowship(listOf(hero(level = 3)), state)
        val enemies = fellowship(listOf(enemy(level = 0)))

        assertThrows<RuntimeException> { heroes.combat(enemies) }
    }
}

private fun hero(name: String = "Hero", level: Int, experience: Double = 0.0) = Character(name, level, experience)
private fun enemy(name: String = "Orc", level: Int) = Character(name = name, level = level, experience = 0.0)
private fun fellowship(members: List<Character> = emptyList(), state: FellowshipState = RESTED) = Fellowship(members, state)