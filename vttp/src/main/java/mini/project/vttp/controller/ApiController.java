package mini.project.vttp.controller;

import lombok.RequiredArgsConstructor;
import mini.project.vttp.entity.News;
import mini.project.vttp.repository.NewsRepository;
import mini.project.vttp.request.NewsPostResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/news")
public class ApiController {
    private final NewsRepository newsRepository;

    @GetMapping("/getnews")
    public ResponseEntity<?> getUser() {
        List<News> news = newsRepository.findAll();
        return ResponseEntity.ok(news);
    }

    @PostMapping("/postnews")
    public ResponseEntity<News> savenews(@RequestBody NewsPostResource newsPostResource) {
        News news = new News();
        news.setContent(newsPostResource.getContent());
        news.setRegion(newsPostResource.getRegion());
        news.setTopic(newsPostResource.getTopic());
        news = newsRepository.save(news);
        return ResponseEntity.ok(news);
    }
}
