# gilded-rose-kotlin
Kotlin refactoring of the Gilded Rose kata — Martin Fowler's classic exercise in safely improving legacy code under test coverage, tackled test-first from an unmodified starting point.

## The Rules

Each item has a `sellIn` value (days remaining to sell it) and a `quality` value (how valuable it is). Every day, both change according to the following rules:

- `sellIn` decreases by 1 for every item, every day.
- `quality` decreases by 1 per day, except:
  - Once `sellIn` has passed zero (i.e. it's negative), `quality` degrades twice as fast.
  - `quality` is never negative and never exceeds 50.

Exceptions to the standard degradation rule:

- **Aged Brie** increases in quality over time instead of decreasing (still capped at 50).
- **Sulfuras** is a legendary item — it never has to be sold, and its quality never changes. It sits outside the normal 0–50 range entirely (conventionally fixed at 80).
- **Backstage passes** increase in quality as the concert approaches:
  - By 1 when there are more than 10 days remaining.
  - By 2 when there are 10 days or fewer remaining.
  - By 3 when there are 5 days or fewer remaining.
  - Quality drops to 0 immediately once the concert has passed.

**Constraint:** The `Item` class itself, and the `items` list, must not be modified at all. All refactoring and the new feature must be implemented entirely within the `GildedRose` class. This mirrors a common real-world situation — working around a dependency you can look at but aren't allowed to change.

## Conjured Items

A new supplier now provides a category of item called "Conjured" — conventionally named **"Conjured Mana Cake"** in this kata's test suite. Conjured items degrade in Quality **twice as fast** as normal items:

- 2 Quality per day before the sell-by date passes (double the normal rate of 1).
- 4 Quality per day once the sell-by date has passed (double the normal post-expiry rate of 2).

The existing floor of 0 still applies — Quality never goes negative, even at the doubled rate.

The same constraint as before still holds: the `Item` class must not be modified. You're free to change `GildedRose` however you see fit, provided every existing test continues to pass.