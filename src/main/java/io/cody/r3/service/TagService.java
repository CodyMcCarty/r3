package io.cody.r3.service;

import io.cody.r3.domain.Article;
import io.cody.r3.domain.Product;
import io.cody.r3.domain.Tag;
import io.cody.r3.model.TagDTO;
import io.cody.r3.repos.ArticleRepository;
import io.cody.r3.repos.ProductRepository;
import io.cody.r3.repos.TagRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TagService {

    private final TagRepository tagRepository;
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    public TagService(final TagRepository tagRepository, final ProductRepository productRepository,
            final ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
    }

    public List<TagDTO> findAll() {
        return tagRepository.findAll()
                .stream()
                .map(tag -> mapToDTO(tag, new TagDTO()))
                .collect(Collectors.toList());
    }

    public TagDTO get(final Long id) {
        return tagRepository.findById(id)
                .map(tag -> mapToDTO(tag, new TagDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final TagDTO tagDTO) {
        final Tag tag = new Tag();
        mapToEntity(tagDTO, tag);
        return tagRepository.save(tag).getId();
    }

    public void update(final Long id, final TagDTO tagDTO) {
        final Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(tagDTO, tag);
        tagRepository.save(tag);
    }

    public void delete(final Long id) {
        tagRepository.deleteById(id);
    }

    private TagDTO mapToDTO(final Tag tag, final TagDTO tagDTO) {
        tagDTO.setId(tag.getId());
        tagDTO.setTag(tag.getTag());
        tagDTO.setProductTags(tag.getProductTags() == null ? null : tag.getProductTags().getId());
        tagDTO.setArticleTag(tag.getArticleTag() == null ? null : tag.getArticleTag().getId());
        return tagDTO;
    }

    private Tag mapToEntity(final TagDTO tagDTO, final Tag tag) {
        tag.setTag(tagDTO.getTag());
        if (tagDTO.getProductTags() != null && (tag.getProductTags() == null || !tag.getProductTags().getId().equals(tagDTO.getProductTags()))) {
            final Product productTags = productRepository.findById(tagDTO.getProductTags())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productTags not found"));
            tag.setProductTags(productTags);
        }
        if (tagDTO.getArticleTag() != null && (tag.getArticleTag() == null || !tag.getArticleTag().getId().equals(tagDTO.getArticleTag()))) {
            final Article articleTag = articleRepository.findById(tagDTO.getArticleTag())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "articleTag not found"));
            tag.setArticleTag(articleTag);
        }
        return tag;
    }

}
