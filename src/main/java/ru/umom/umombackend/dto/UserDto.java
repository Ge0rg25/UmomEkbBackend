package ru.umom.umombackend.dto;

public class UserDto {

    private interface Id{
        String id();
    }

    private interface Name {
        String name();
    }

    private interface Floor{
        short floor();
    }

    private interface Workstation {
        short workstation();
    }

    public static class Request {
        public record Update(String id, String name, short floor, short workstation) implements Id, Name, Floor, Workstation{}
        public record Get(String id) implements Id {}

    }

    public static class Response {
        public record BaseResponse(String id, String name, short floor, short workstation) implements Id, Name, Floor, Workstation {}
    }



}
