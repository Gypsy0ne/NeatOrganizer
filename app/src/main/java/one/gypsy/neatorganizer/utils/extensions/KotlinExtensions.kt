package one.gypsy.neatorganizer.utils.extensions

import kotlinx.coroutines.delay

suspend fun delayItemsEmission(itemsCount: Int) = delay(
    (itemsCount * SINGLE_ITEM_MILLIS_DELAY_FACTOR).coerceAtMost(MAXIMUM_MILLIS_DELAY).toLong()
)

private const val SINGLE_ITEM_MILLIS_DELAY_FACTOR = 50
private const val MAXIMUM_MILLIS_DELAY = 400
