package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.parameters.P;

public class NewsDto {

    private interface Id{
        String id();
    }

    private interface Title {
        String title();
    }

    private interface Body {
        String body();
    }

    private interface PhotoId {
        @JsonProperty("photo_id")
        String photoId();
    }


    public static class Request{
        public record Create(String title, String body, String photoId) implements Title, Body, PhotoId{}
        public record Update(String id, String title, String body, String photoId) implements Id, Title, Body, PhotoId{}
    }

    public static class Response {
        public record News(String id, String title, String body, String photoId) implements Id, Title, Body, PhotoId{}
    }



}
