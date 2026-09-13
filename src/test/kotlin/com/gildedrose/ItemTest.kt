package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ItemTest {

    @Test
    fun `can check fields on an item`() {
        val item = Item("Judgement Spaulders", 30, 50)

        assertAll(
            { assertEquals("Judgement Spaulders", item.name) },
            { assertEquals(30, item.sellIn) },
            { assertEquals(50, item.quality) },
        )
    }

    @Test
    fun `toString reflects the item's fields`() {
        val item = Item("Elixir of the Mongoose", 5, 7)

        assertEquals("Elixir of the Mongoose, 5, 7", item.toString())
    }
}