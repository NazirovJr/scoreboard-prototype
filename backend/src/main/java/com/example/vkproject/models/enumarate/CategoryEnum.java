package com.example.vkproject.models.enumarate;

public enum CategoryEnum {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public static boolean contains(String test) {

        for (CategoryEnum c : CategoryEnum.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

    public String getCategory() {
        return this.category;
    }
}
