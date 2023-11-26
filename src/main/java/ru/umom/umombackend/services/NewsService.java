package ru.umom.umombackend.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.NewsDto;
import ru.umom.umombackend.models.NewsEntity;
import ru.umom.umombackend.repositories.NewsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewsService {

    NewsRepository newsRepository;

    public void create(NewsDto.Request.Create dto){
        NewsEntity newsEntity = NewsEntity.builder()
                .title(dto.title())
                .body(dto.body())
                .photoId(dto.photoId())
                .build();

        newsRepository.save(newsEntity);

    }

    public List<NewsDto.Response.News> getAll(){
        List<NewsEntity> newsEntities = newsRepository.findAll();
        List<NewsDto.Response.News> response = new ArrayList<>();

        for(NewsEntity news: newsEntities){
            response.add(new NewsDto.Response.News(news.getId(), news.getTitle(), news.getBody(), news.getPhotoId()));
        }
        return response;

    }


}
