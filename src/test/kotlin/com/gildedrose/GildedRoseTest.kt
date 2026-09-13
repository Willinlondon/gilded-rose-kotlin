package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GildedRoseTest {

    @Test
    fun `sellIn should decrease by 1 for every item, every day`() {
        val elixirOfTheMongoose = Item("Elixir of the Mongoose", 30, 30)
        val judgementSpaulders = Item("Judgement Spaulders", 1, 1)
        val gildedRose = GildedRose(listOf(elixirOfTheMongoose, judgementSpaulders))

        gildedRose.updateQuality()

        assertAll(
            { assertEquals(29, gildedRose.items[0].sellIn) },
            { assertEquals(0, gildedRose.items[1].sellIn) }
        )
    }

    @Test
    fun `sellIn can be negative`() {
        val elixirOfTheMongoose = Item("Elixir of the Mongoose", 0, 30)
        val gildedRose = GildedRose(listOf(elixirOfTheMongoose))

        gildedRose.updateQuality()

        assertEquals(-1, gildedRose.items[0].sellIn)
    }

    @Test
    fun `quality should decrease by 1 for every item, every day`() {
        val elixirOfTheMongoose = Item("Elixir of the Mongoose", 30, 30)
        val judgementSpaulders = Item("Judgement Spaulders", 1, 1)
        val gildedRose = GildedRose(listOf(elixirOfTheMongoose, judgementSpaulders))

        gildedRose.updateQuality()

        assertAll(
            { assertEquals(29, gildedRose.items[0].quality) },
            { assertEquals(0, gildedRose.items[1].quality) }
        )
    }

    @Test
    fun `quality can not be negative`() {
        val elixirOfTheMongoose = Item("Elixir of the Mongoose", 10, 0)
        val gildedRose = GildedRose(listOf(elixirOfTheMongoose))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)

    }

    @Test
    fun `quality degrades twice as fast when sellIn is 0`() {
        val elixirOfTheMongoose = Item("Elixir of the Mongoose", 0, 10)
        val gildedRose = GildedRose(listOf(elixirOfTheMongoose))

        gildedRose.updateQuality()

        assertEquals(8, gildedRose.items[0].quality)

    }

    @Test
    fun `Aged Brie increases by 1 per day instead of decreasing`() {
        val agedBrie = Item("Aged Brie", 10, 40)
        val gildedRose = GildedRose(listOf(agedBrie))

        gildedRose.updateQuality()

        assertEquals(41, gildedRose.items[0].quality)
    }

    @Test
    fun `Aged Brie cannot increase above 50`() {
        val agedBrie = Item("Aged Brie", 10, 50)
        val gildedRose = GildedRose(listOf(agedBrie))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun `Aged Brie doubles in quality increase when its sellIn is 0`() {
        val agedBrie = Item("Aged Brie", 0, 20)
        val gildedRose = GildedRose(listOf(agedBrie))

        gildedRose.updateQuality()

        assertEquals(22, gildedRose.items[0].quality)
    }

    @Test
    fun `Sulfuras, Hand of Ragnaros has fixed quality range of 80 neither quality nor sellIn can be lowered`() {
        val sulfuras = Item("Sulfuras, Hand of Ragnaros", 1, 80)
        val gildedRose = GildedRose(listOf(sulfuras))

        gildedRose.updateQuality()

        assertAll(
            { assertEquals(80, gildedRose.items[0].quality) },
            { assertEquals(1, gildedRose.items[0].sellIn) }
        )
    }

    @Test
    fun `Backstage passes increase in quality by 1 when there are more than 10 days remaining`() {
        val backstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)
        val gildedRose = GildedRose(listOf(backstagePasses))

        gildedRose.updateQuality()

        assertEquals(11, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage passes increase in quality by 2 when there are between 5 and 10 days remaining`() {
        val firstBackstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 6, 10)
        val secondBackstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)
        val gildedRose = GildedRose(listOf(firstBackstagePasses, secondBackstagePasses))

        gildedRose.updateQuality()

        assertAll(
            { assertEquals(12, gildedRose.items[0].quality) },
            { assertEquals(12, gildedRose.items[1].quality) }
        )
    }

    @Test
    fun `Backstage passes increase in quality by 3 when there are between 5 or fewer days remaining`() {
        val firstBackstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        val secondBackstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 1, 10)
        val gildedRose = GildedRose(listOf(firstBackstagePasses, secondBackstagePasses))

        gildedRose.updateQuality()

        assertAll(
            { assertEquals(13, gildedRose.items[0].quality) },
            { assertEquals(13, gildedRose.items[1].quality) }
        )
    }

    @Test
    fun `Backstage passes decrease in quality to 0 once sellin is also 0`() {
        val backstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)
        val gildedRose = GildedRose(listOf(backstagePasses))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun `Backstage passes cannot increase quality above 50`() {
        val backstagePasses = Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)
        val gildedRose = GildedRose(listOf(backstagePasses))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }
}