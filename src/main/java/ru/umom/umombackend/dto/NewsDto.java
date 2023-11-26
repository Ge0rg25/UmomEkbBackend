package ru.umom.umombackend.dto;

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
