package com.tomtom.game

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CampfireTest {
    @ParameterizedTest(name = "when rest is made in {0} state then after rest it should be in {1} state")
    @CsvSource(delimiterString = "--->",
        textBlock = """
        RESTED ---> RESTED
        NEEDS_REPAIR ---> NEEDS_REPAIR
        EXHAUSTED ---> RESTED
        LOW_MORALE ---> EXHAUSTED"""
    )
    fun `rest conditions`(beginState: FellowshipState, expectedState: FellowshipState) {
        assertEquals(expectedState, buildCampfire(beginState))
    }
}