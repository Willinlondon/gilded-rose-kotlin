package com.gildedrose

private const val MINIMUM_QUALITY = 0
private const val MAX_QUALITY = 50
private const val SELL_IN_EXPIRED = 0
private const val CONJURED_DEGRADE_RATE = 2
private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
private const val AGED_BRIE = "Aged Brie"
private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
private const val CONJURED_MANA_CAKE = "Conjured Mana Cake"

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            when (item.name) {
                SULFURAS -> return@forEach
                BACKSTAGE_PASSES -> {
                    backstagePassesQualityCalculator(item)
                    reduceSellIn(item)
                }

                AGED_BRIE -> {
                    increaseQuality(item)
                    applyIfExpired(item) { increaseQuality(item) }
                    reduceSellIn(item)
                }

                CONJURED_MANA_CAKE -> {
                    decreaseQuality(item,CONJURED_DEGRADE_RATE)
                    applyIfExpired(item) { decreaseQuality(item, CONJURED_DEGRADE_RATE) }
                    reduceSellIn(item)
                }

                else -> {
                    decreaseQuality(item)
                    applyIfExpired(item) { decreaseQuality(item) }
                    reduceSellIn(item)
                }
            }
        }
    }

    private fun increaseQuality(item: Item, amount: Int = 1) = with(item) {
        quality = (quality + amount).coerceAtMost(MAX_QUALITY)
    }

    private fun decreaseQuality(item: Item, amount: Int = 1) = with(item) {
        quality = (quality - amount).coerceAtLeast(MINIMUM_QUALITY)
    }

    private fun reduceSellIn(item: Item) = with(item) {
        sellIn--
    }

    private fun backstagePassesQualityCalculator(item: Item) = with(item) {
        when {
            sellIn <= SELL_IN_EXPIRED -> quality = MINIMUM_QUALITY
            sellIn <= 5 -> increaseQuality(item, 3)
            sellIn <= 10 -> increaseQuality(item, 2)
            else -> increaseQuality(item)
        }
    }

    private fun applyIfExpired(item: Item, action: () -> Unit) = with(item) {
        if (sellIn <= SELL_IN_EXPIRED) {
            action()
        }
    }
}