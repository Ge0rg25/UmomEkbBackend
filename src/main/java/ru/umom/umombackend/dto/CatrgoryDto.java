package ru.umom.umombackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatrgoryDto {

    private interface Id {
        String id();
    }

    private interface Title{
        String title();
    }

    private interface Description  {
        String description();
    }

    private interface PhotoId{
        @JsonProperty("photo_id")
        String photoId();
    }

    private interface OrganizationId{
        @JsonProperty("organization_id")
        String organizationId();
    }


    public static class Request{
        public record Create(String title, String description, String photoId, String organizationId) implements Title, Description, PhotoId, OrganizationId{}
        public record Update(String id,String title, String description, String photoId) implements Id, Title, Description, PhotoId{}
        public record Delete(String id) implements Id{}

        public record GetByOrganization(String organizationId) implements OrganizationId{



        }
    }


    public static class Response {
        public record BaseResponse(String id,String title, String description, String photoId) implements Id, Title, Description, PhotoId{}
    }

}
