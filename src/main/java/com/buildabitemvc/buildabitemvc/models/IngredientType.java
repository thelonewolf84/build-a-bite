package com.buildabitemvc.buildabitemvc.models;

public enum IngredientType {

    DAIRY("Dairy"),
    VEGETABLE("Vegetable"),
    GRAINS("Grains"),
    SWEETENER("Sweetener"),
    SPICE("Spice"),
    MEAT("Meat"),
    FISH("Fish"),
    SEAFOOD("Seafood"),
    OILS("Oils"),
    CONDIMENT("Condiment"),
    SEASONING("Seasoning"),
    SAUCE("Sauce"),
    LEGUME("Legume"),
    ALCOHOL("Alcohol"),
    SOUP("Soup"),
    NUT("Nut"),
    DAIRY_ALTERNATIVE("Dairy Alternative"),
    DESSERT("Dessert"),
    BEVERAGE("Beverage");


    private final String displayName;

    IngredientType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
