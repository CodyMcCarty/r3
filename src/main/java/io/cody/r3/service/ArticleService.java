package io.cody.r3.service;

import io.cody.r3.domain.Article;
import io.cody.r3.model.ArticleDTO;
import io.cody.r3.repos.ArticleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleDTO> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(article -> mapToDTO(article, new ArticleDTO()))
                .collect(Collectors.toList());
    }

    public ArticleDTO get(final Long id) {
        return articleRepository.findById(id)
                .map(article -> mapToDTO(article, new ArticleDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ArticleDTO articleDTO) {
        final Article article = new Article();
        mapToEntity(articleDTO, article);
        return articleRepository.save(article).getId();
    }

    public void update(final Long id, final ArticleDTO articleDTO) {
        final Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(articleDTO, article);
        articleRepository.save(article);
    }

    public void delete(final Long id) {
        articleRepository.deleteById(id);
    }

    private ArticleDTO mapToDTO(final Article article, final ArticleDTO articleDTO) {
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setLinks(article.getLinks());
        articleDTO.setBody(article.getBody());
        return articleDTO;
    }

    private Article mapToEntity(final ArticleDTO articleDTO, final Article article) {
        article.setTitle(articleDTO.getTitle());
        article.setLinks(articleDTO.getLinks());
        article.setBody(articleDTO.getBody());
        return article;
    }

}
