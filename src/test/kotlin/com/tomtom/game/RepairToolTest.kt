package com.tomtom.game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RepairToolTest {
    @ParameterizedTest(name = "when repair is made in {0} state then after repair it should be in {1} state")
    @CsvSource(delimiterString = "->",
        textBlock = """
        RESTED -> RESTED
        NEEDS_REPAIR -> RESTED
        EXHAUSTED -> EXHAUSTED
        LOW_MORALE -> LOW_MORALE"""
    )
    fun `repair conditions`(beginState: FellowshipState, expectedState: FellowshipState) {
        assertEquals(expectedState, useRepairTools(beginState))
    }
}