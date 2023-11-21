package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


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

    private interface CPFC {
        double cpfc();
    }

    private interface CategoryId {
        @JsonProperty("category_id")
        String categoryId();
    }

    public static class Request {
        public record Create(String title, String description, double price, double cpfc, String categoryId) implements Title, Description, Price, CPFC, CategoryId{}

        public record Update(String id, String title, String description, double price, double cpfc) implements Id, Title, Description, Price, CPFC{}

        public record Delete(String id) implements Id{}

        public record GetByCategory(String categoryId) implements CategoryId{}

    }

    public static class Response {
        public record BaseResponse(String id, String title, String description, double price, double cpfc, String categoryId) implements Id, Title, Description, Price, CPFC, CategoryId{}
    }
}
