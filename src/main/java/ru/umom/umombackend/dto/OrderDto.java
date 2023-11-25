package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.parameters.P;

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
        String organizationId();
    }

    private interface CreatedTimestamp {
        Timestamp createdTimestamp();
    }

    public static class Request{

        public record Create(boolean delivery, List<String> dishesId) implements Delivery, DishesId{}

        public record Complete(String id) implements Id {}

        public record GetAllByOrganization(String organizationId) implements OrganizationId{}

    }

    public static class Response {
        public record BaseResponse(String id, boolean delivery, List<DishDto.Response.Dish> dishes, UserDto.Response.User user,Timestamp createdTimestamp, boolean isCompleted) implements Id, Delivery, Dishes, User, CreatedTimestamp,IsCompleted {}
    }
}
