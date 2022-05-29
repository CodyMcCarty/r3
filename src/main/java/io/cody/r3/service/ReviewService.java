package io.cody.r3.service;

import io.cody.r3.domain.Product;
import io.cody.r3.domain.Review;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.ReviewDTO;
import io.cody.r3.repos.ProductRepository;
import io.cody.r3.repos.ReviewRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;

    public ReviewService(final ReviewRepository reviewRepository,
            final VendorRepository vendorRepository, final ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.vendorRepository = vendorRepository;
        this.productRepository = productRepository;
    }

    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .collect(Collectors.toList());
    }

    public ReviewDTO get(final Long id) {
        return reviewRepository.findById(id)
                .map(review -> mapToDTO(review, new ReviewDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ReviewDTO reviewDTO) {
        final Review review = new Review();
        mapToEntity(reviewDTO, review);
        return reviewRepository.save(review).getId();
    }

    public void update(final Long id, final ReviewDTO reviewDTO) {
        final Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(reviewDTO, review);
        reviewRepository.save(review);
    }

    public void delete(final Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO mapToDTO(final Review review, final ReviewDTO reviewDTO) {
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setBody(review.getBody());
        reviewDTO.setVendorReviews(review.getVendorReviews() == null ? null : review.getVendorReviews().getId());
        reviewDTO.setProductReviews(review.getProductReviews() == null ? null : review.getProductReviews().getId());
        return reviewDTO;
    }

    private Review mapToEntity(final ReviewDTO reviewDTO, final Review review) {
        review.setRating(reviewDTO.getRating());
        review.setTitle(reviewDTO.getTitle());
        review.setBody(reviewDTO.getBody());
        if (reviewDTO.getVendorReviews() != null && (review.getVendorReviews() == null || !review.getVendorReviews().getId().equals(reviewDTO.getVendorReviews()))) {
            final Vendor vendorReviews = vendorRepository.findById(reviewDTO.getVendorReviews())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorReviews not found"));
            review.setVendorReviews(vendorReviews);
        }
        if (reviewDTO.getProductReviews() != null && (review.getProductReviews() == null || !review.getProductReviews().getId().equals(reviewDTO.getProductReviews()))) {
            final Product productReviews = productRepository.findById(reviewDTO.getProductReviews())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productReviews not found"));
            review.setProductReviews(productReviews);
        }
        return review;
    }

}
