package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.sql.Timestamp;
import java.util.List;

public class OrderDto {
    private interface Id {
        String id();
    }

    private interface Delivery{
        boolean delivery();
    }

    private interface IsCompleted{
        @JsonProperty("is_completed")
        boolean isCompleted();
    }

    private interface User {
        UserDto.Response.User user();
    }

    private interface DishesId {
        @JsonProperty("dishes_id")
        List<String> dishesId();
    }

    private interface Dishes {
        List<DishDto.Response.Dish> dishes();
    }

    private interface OrganizationId {
        @JsonProperty("organization_id")
        String organizationId();
    }

    private interface CreatedTimestamp {
        @JsonProperty("created_timestamp")
        Timestamp createdTimestamp();
    }

    public static class Request{

        public record Create(boolean delivery, List<String> dishesId, String organizationId) implements Delivery, DishesId, OrganizationId{}

        public record Complete(String id) implements Id {}

        public record GetAllByOrganization(String organizationId) implements OrganizationId{}

    }

    public static class Response {
        public record BaseResponse(String id, boolean delivery, List<DishDto.Response.Dish> dishes, UserDto.Response.User user,Timestamp createdTimestamp, boolean isCompleted) implements Id, Delivery, Dishes, User, CreatedTimestamp,IsCompleted {}
    }
}
