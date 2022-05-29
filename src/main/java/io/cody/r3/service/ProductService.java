package io.cody.r3.service;

import io.cody.r3.domain.Customer;
import io.cody.r3.domain.Product;
import io.cody.r3.domain.Purchase;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.ProductDTO;
import io.cody.r3.repos.CustomerRepository;
import io.cody.r3.repos.ProductRepository;
import io.cody.r3.repos.PurchaseRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;

    public ProductService(final ProductRepository productRepository,
            final VendorRepository vendorRepository, final PurchaseRepository purchaseRepository,
            final CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .collect(Collectors.toList());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantityAvaliable(product.getQuantityAvaliable());
        productDTO.setPrice(product.getPrice());
        productDTO.setIsProductActive(product.getIsProductActive());
        productDTO.setRelavance(product.getRelavance());
        productDTO.setVendorProducts(product.getVendorProducts() == null ? null : product.getVendorProducts().getId());
        productDTO.setPurchaseCart(product.getPurchaseCart() == null ? null : product.getPurchaseCart().getId());
        productDTO.setFavorites(product.getFavorites() == null ? null : product.getFavorites().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantityAvaliable(productDTO.getQuantityAvaliable());
        product.setPrice(productDTO.getPrice());
        product.setIsProductActive(productDTO.getIsProductActive());
        product.setRelavance(productDTO.getRelavance());
        if (productDTO.getVendorProducts() != null && (product.getVendorProducts() == null || !product.getVendorProducts().getId().equals(productDTO.getVendorProducts()))) {
            final Vendor vendorProducts = vendorRepository.findById(productDTO.getVendorProducts())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorProducts not found"));
            product.setVendorProducts(vendorProducts);
        }
        if (productDTO.getPurchaseCart() != null && (product.getPurchaseCart() == null || !product.getPurchaseCart().getId().equals(productDTO.getPurchaseCart()))) {
            final Purchase purchaseCart = purchaseRepository.findById(productDTO.getPurchaseCart())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "purchaseCart not found"));
            product.setPurchaseCart(purchaseCart);
        }
        if (productDTO.getFavorites() != null && (product.getFavorites() == null || !product.getFavorites().getId().equals(productDTO.getFavorites()))) {
            final Customer favorites = customerRepository.findById(productDTO.getFavorites())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "favorites not found"));
            product.setFavorites(favorites);
        }
        return product;
    }

}
