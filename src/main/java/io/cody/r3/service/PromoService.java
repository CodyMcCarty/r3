package io.cody.r3.service;

import io.cody.r3.domain.Product;
import io.cody.r3.domain.Promo;
import io.cody.r3.model.PromoDTO;
import io.cody.r3.repos.ProductRepository;
import io.cody.r3.repos.PromoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PromoService {

    private final PromoRepository promoRepository;
    private final ProductRepository productRepository;

    public PromoService(final PromoRepository promoRepository,
            final ProductRepository productRepository) {
        this.promoRepository = promoRepository;
        this.productRepository = productRepository;
    }

    public List<PromoDTO> findAll() {
        return promoRepository.findAll()
                .stream()
                .map(promo -> mapToDTO(promo, new PromoDTO()))
                .collect(Collectors.toList());
    }

    public PromoDTO get(final Long id) {
        return promoRepository.findById(id)
                .map(promo -> mapToDTO(promo, new PromoDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PromoDTO promoDTO) {
        final Promo promo = new Promo();
        mapToEntity(promoDTO, promo);
        return promoRepository.save(promo).getId();
    }

    public void update(final Long id, final PromoDTO promoDTO) {
        final Promo promo = promoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(promoDTO, promo);
        promoRepository.save(promo);
    }

    public void delete(final Long id) {
        promoRepository.deleteById(id);
    }

    private PromoDTO mapToDTO(final Promo promo, final PromoDTO promoDTO) {
        promoDTO.setId(promo.getId());
        promoDTO.setPromoName(promo.getPromoName());
        promoDTO.setPromoCode(promo.getPromoCode());
        promoDTO.setExpirationDate(promo.getExpirationDate());
        promoDTO.setStartDate(promo.getStartDate());
        promoDTO.setNumOfDiscounts(promo.getNumOfDiscounts());
        promoDTO.setDiscountAmmount(promo.getDiscountAmmount());
        promoDTO.setDiscontPrecentage(promo.getDiscontPrecentage());
        promoDTO.setIsActive(promo.getIsActive());
        promoDTO.setAbout(promo.getAbout());
        promoDTO.setProductPromo(promo.getProductPromo() == null ? null : promo.getProductPromo().getId());
        return promoDTO;
    }

    private Promo mapToEntity(final PromoDTO promoDTO, final Promo promo) {
        promo.setPromoName(promoDTO.getPromoName());
        promo.setPromoCode(promoDTO.getPromoCode());
        promo.setExpirationDate(promoDTO.getExpirationDate());
        promo.setStartDate(promoDTO.getStartDate());
        promo.setNumOfDiscounts(promoDTO.getNumOfDiscounts());
        promo.setDiscountAmmount(promoDTO.getDiscountAmmount());
        promo.setDiscontPrecentage(promoDTO.getDiscontPrecentage());
        promo.setIsActive(promoDTO.getIsActive());
        promo.setAbout(promoDTO.getAbout());
        if (promoDTO.getProductPromo() != null && (promo.getProductPromo() == null || !promo.getProductPromo().getId().equals(promoDTO.getProductPromo()))) {
            final Product productPromo = productRepository.findById(promoDTO.getProductPromo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productPromo not found"));
            promo.setProductPromo(productPromo);
        }
        return promo;
    }

}
