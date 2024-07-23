package ru.car.washing.model;

public enum Role {

    ROLE_USER("Пользователь"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_OPERATOR("Оператор");
    private String title;

    Role(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}
