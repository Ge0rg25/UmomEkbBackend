package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public class OrderDto {
    private interface Id {
        String id();
    }

    private interface Delivery{
        boolean delivery();
    }

    private interface UserId {
        @JsonProperty("user_id")
        String userId();
    }

    private interface User {
        UserDto.Response.User user();
    }

    private interface DishesId {
        @JsonProperty("dishes_id")
        List<String> dishesId();
    }

    private interface OrganizationId {
        String organizationId();
    }

    public static class Request{

        public record Create(boolean delivery, String userId, List<String> dishesId) implements Delivery, UserId, DishesId{}

        public record Delete(String id) implements Id {}

        public record GetAllByOrganization(String organizationId) implements OrganizationId{}

    }

    public static class Response {
        public record UserOrders(String id, boolean delivery, List<String> dishesId) implements Id, Delivery, DishesId {}
        public record AdminOrders(String id, UserDto.Response.User user, List<String> dishesId, boolean delivery) implements Id, User, DishesId, Delivery {}
    }
}
