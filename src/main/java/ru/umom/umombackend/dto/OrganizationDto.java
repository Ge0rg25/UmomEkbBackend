package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class OrganizationDto {
    private interface Id {
        String id();
    }

    public interface Title {
        String title();
    }

    public interface Description{
        String description();
    }

    public interface Address{
        String address();
    }

    public interface PhotoId {
        @JsonProperty("photo_id")
        String photoId();
    }

    public static class Request {

        public record Create(String title, String description, String address, String photoId) implements Title, Description, Address, PhotoId{}

        public record Update(String id,String title, String description, String address, String photoId) implements Id, Title, Description, Address, PhotoId{}

        public record Delete(String id) implements Id{}

    }

    public static class Response {
        public record BaseResponse(String id,String title, String description, String address, String photoId) implements Id, Title, Description, Address, PhotoId{}

    }

}
