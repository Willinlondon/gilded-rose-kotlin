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

The goal of the kata is to add a **new** item type ("Conjured" items, which degrade in quality twice as fast as normal items) without making the existing code any harder to understand than it already is — which is only realistically possible once genuine test coverage is in place first.