package com.codecool.ehotel.model;

import static com.codecool.ehotel.model.MealDurability.*;

public enum MealType {
    SCRAMBLED_EGGS (15, SHORT),
    SUNNY_SIDE_UP (15, SHORT),
    FRIED_SAUSAGE(30, SHORT),
    FRIED_BACON(25, SHORT),
    PANCAKE(20, SHORT),
    CROISSANT(20, SHORT),
    MASHED_POTATO(10, MEDIUM),
    MUFFIN(15, MEDIUM),
    BUN(5, MEDIUM),
    CEREAL(10, LONG),
    MILK(5, LONG);

    private int cost;
    private MealDurability mealDurability;

    MealType(int cost, MealDurability mealDurability) {
        this.cost = cost;
        this.mealDurability = mealDurability;
    }

    public int getCost() {
        return cost;
    }

    public MealDurability getDurability() {
        return mealDurability;
    }
}
