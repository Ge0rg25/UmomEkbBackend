package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.parameters.P;


public class DishDto {

    private interface Id {
        String id();
    }

    private interface Title{
        String title();
    }

    private interface Description{
        String description();
    }

    private interface Price{
        double price();
    }

    private interface Calories {
        double calories();
    }

    private interface Proteins {
        double proteins();
    }

    private interface Fats {
        double fats();
    }

    private interface Carbohydrates{
        double carbohydrates();
    }

    private interface CategoryId {
        @JsonProperty("category_id")
        String categoryId();
    }

    private interface PhotoId{
        @JsonProperty("photo_id")
        String photoId();
    }

    public static class Request {
        public record Create(String title, String description, double price, double calories, double proteins, double fats, double carbohydrates, String categoryId, String photoId) implements Title, Description, Price, Calories, Proteins, Fats, Carbohydrates, CategoryId, PhotoId{}

        public record Update(String id, String title, String description, double price, double calories, double proteins, double fats, double carbohydrates, String photoId) implements Id, Title, Description, Price, Calories, Proteins, Fats, Carbohydrates, PhotoId{}

        public record Delete(String id) implements Id{}

        public record GetByCategory(String categoryId) implements CategoryId{}

    }

    public static class Response {
        public record Dish(String id, String title, String description, double price, double calories, double proteins, double fats, double carbohydrates, String categoryId, String photoId) implements Id, Title, Description, Price, Calories, Proteins, Fats, Carbohydrates,  CategoryId, PhotoId{}
    }
}
