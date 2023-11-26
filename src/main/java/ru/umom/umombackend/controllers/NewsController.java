package ru.umom.umombackend.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.umom.umombackend.dto.NewsDto;
import ru.umom.umombackend.services.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewsController {

    NewsService newsService;


    @PutMapping("/create")
    public void create(@RequestBody @Validated NewsDto.Request.Create dto){
        newsService.create(dto);
    }

    @GetMapping("/get")
    public List<NewsDto.Response.News> get(){
        return newsService.getAll();
    }

}
