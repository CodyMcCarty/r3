package io.cody.r3.service;

import io.cody.r3.domain.Image;
import io.cody.r3.domain.Product;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.ImageDTO;
import io.cody.r3.repos.ImageRepository;
import io.cody.r3.repos.ProductRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;

    public ImageService(final ImageRepository imageRepository,
            final ProductRepository productRepository, final VendorRepository vendorRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<ImageDTO> findAll() {
        return imageRepository.findAll()
                .stream()
                .map(image -> mapToDTO(image, new ImageDTO()))
                .collect(Collectors.toList());
    }

    public ImageDTO get(final Long id) {
        return imageRepository.findById(id)
                .map(image -> mapToDTO(image, new ImageDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ImageDTO imageDTO) {
        final Image image = new Image();
        mapToEntity(imageDTO, image);
        return imageRepository.save(image).getId();
    }

    public void update(final Long id, final ImageDTO imageDTO) {
        final Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(imageDTO, image);
        imageRepository.save(image);
    }

    public void delete(final Long id) {
        imageRepository.deleteById(id);
    }

    private ImageDTO mapToDTO(final Image image, final ImageDTO imageDTO) {
        imageDTO.setId(image.getId());
        imageDTO.setIsPrimary(image.getIsPrimary());
        imageDTO.setDescription(image.getDescription());
        imageDTO.setImage(image.getImage());
        imageDTO.setProductImages(image.getProductImages() == null ? null : image.getProductImages().getId());
        imageDTO.setVendorImages(image.getVendorImages() == null ? null : image.getVendorImages().getId());
        return imageDTO;
    }

    private Image mapToEntity(final ImageDTO imageDTO, final Image image) {
        image.setIsPrimary(imageDTO.getIsPrimary());
        image.setDescription(imageDTO.getDescription());
        image.setImage(imageDTO.getImage());
        if (imageDTO.getProductImages() != null && (image.getProductImages() == null || !image.getProductImages().getId().equals(imageDTO.getProductImages()))) {
            final Product productImages = productRepository.findById(imageDTO.getProductImages())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productImages not found"));
            image.setProductImages(productImages);
        }
        if (imageDTO.getVendorImages() != null && (image.getVendorImages() == null || !image.getVendorImages().getId().equals(imageDTO.getVendorImages()))) {
            final Vendor vendorImages = vendorRepository.findById(imageDTO.getVendorImages())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorImages not found"));
            image.setVendorImages(vendorImages);
        }
        return image;
    }

}
