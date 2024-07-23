package ru.car.washing.model;

public enum Role {

    ROLE_USER("Пользователь"),
    ROLE_ADMINISTRATOR("Администратор"),
    ROLE_OPERATOR("Оператор"),
    ROLE_ANONYMOUS_USER("Анонимный пользователь");
    private String title;

    Role(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}
