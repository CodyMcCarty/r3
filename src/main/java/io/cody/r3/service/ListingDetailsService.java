package io.cody.r3.service;

import io.cody.r3.domain.ListingDetails;
import io.cody.r3.domain.Product;
import io.cody.r3.model.ListingDetailsDTO;
import io.cody.r3.repos.ListingDetailsRepository;
import io.cody.r3.repos.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ListingDetailsService {

    private final ListingDetailsRepository listingDetailsRepository;
    private final ProductRepository productRepository;

    public ListingDetailsService(final ListingDetailsRepository listingDetailsRepository,
            final ProductRepository productRepository) {
        this.listingDetailsRepository = listingDetailsRepository;
        this.productRepository = productRepository;
    }

    public List<ListingDetailsDTO> findAll() {
        return listingDetailsRepository.findAll()
                .stream()
                .map(listingDetails -> mapToDTO(listingDetails, new ListingDetailsDTO()))
                .collect(Collectors.toList());
    }

    public ListingDetailsDTO get(final Long id) {
        return listingDetailsRepository.findById(id)
                .map(listingDetails -> mapToDTO(listingDetails, new ListingDetailsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ListingDetailsDTO listingDetailsDTO) {
        final ListingDetails listingDetails = new ListingDetails();
        mapToEntity(listingDetailsDTO, listingDetails);
        return listingDetailsRepository.save(listingDetails).getId();
    }

    public void update(final Long id, final ListingDetailsDTO listingDetailsDTO) {
        final ListingDetails listingDetails = listingDetailsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(listingDetailsDTO, listingDetails);
        listingDetailsRepository.save(listingDetails);
    }

    public void delete(final Long id) {
        listingDetailsRepository.deleteById(id);
    }

    private ListingDetailsDTO mapToDTO(final ListingDetails listingDetails,
            final ListingDetailsDTO listingDetailsDTO) {
        listingDetailsDTO.setId(listingDetails.getId());
        listingDetailsDTO.setWhoMade(listingDetails.getWhoMade());
        listingDetailsDTO.setIsHandMade(listingDetails.getIsHandMade());
        listingDetailsDTO.setIsAutoRenewed(listingDetails.getIsAutoRenewed());
        listingDetailsDTO.setSKU(listingDetails.getSKU());
        listingDetailsDTO.setHasColors(listingDetails.getHasColors());
        listingDetailsDTO.setHasSecondaryColor(listingDetails.getHasSecondaryColor());
        listingDetailsDTO.setHasSizes(listingDetails.getHasSizes());
        listingDetailsDTO.setHasMaterials(listingDetails.getHasMaterials());
        listingDetailsDTO.setHasGenders(listingDetails.getHasGenders());
        listingDetailsDTO.setHasPersonalization(listingDetails.getHasPersonalization());
        listingDetailsDTO.setHasOtherfield(listingDetails.getHasOtherfield());
        listingDetailsDTO.setColors(listingDetails.getColors());
        listingDetailsDTO.setSizes(listingDetails.getSizes());
        listingDetailsDTO.setMaterial(listingDetails.getMaterial());
        listingDetailsDTO.setGenders(listingDetails.getGenders());
        listingDetailsDTO.setSecondaryColor(listingDetails.getSecondaryColor());
        listingDetailsDTO.setPersonalization(listingDetails.getPersonalization());
        listingDetailsDTO.setOtherfields(listingDetails.getOtherfields());
        listingDetailsDTO.setProductDetails(listingDetails.getProductDetails() == null ? null : listingDetails.getProductDetails().getId());
        return listingDetailsDTO;
    }

    private ListingDetails mapToEntity(final ListingDetailsDTO listingDetailsDTO,
            final ListingDetails listingDetails) {
        listingDetails.setWhoMade(listingDetailsDTO.getWhoMade());
        listingDetails.setIsHandMade(listingDetailsDTO.getIsHandMade());
        listingDetails.setIsAutoRenewed(listingDetailsDTO.getIsAutoRenewed());
        listingDetails.setSKU(listingDetailsDTO.getSKU());
        listingDetails.setHasColors(listingDetailsDTO.getHasColors());
        listingDetails.setHasSecondaryColor(listingDetailsDTO.getHasSecondaryColor());
        listingDetails.setHasSizes(listingDetailsDTO.getHasSizes());
        listingDetails.setHasMaterials(listingDetailsDTO.getHasMaterials());
        listingDetails.setHasGenders(listingDetailsDTO.getHasGenders());
        listingDetails.setHasPersonalization(listingDetailsDTO.getHasPersonalization());
        listingDetails.setHasOtherfield(listingDetailsDTO.getHasOtherfield());
        listingDetails.setColors(listingDetailsDTO.getColors());
        listingDetails.setSizes(listingDetailsDTO.getSizes());
        listingDetails.setMaterial(listingDetailsDTO.getMaterial());
        listingDetails.setGenders(listingDetailsDTO.getGenders());
        listingDetails.setSecondaryColor(listingDetailsDTO.getSecondaryColor());
        listingDetails.setPersonalization(listingDetailsDTO.getPersonalization());
        listingDetails.setOtherfields(listingDetailsDTO.getOtherfields());
        if (listingDetailsDTO.getProductDetails() != null && (listingDetails.getProductDetails() == null || !listingDetails.getProductDetails().getId().equals(listingDetailsDTO.getProductDetails()))) {
            final Product productDetails = productRepository.findById(listingDetailsDTO.getProductDetails())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "productDetails not found"));
            listingDetails.setProductDetails(productDetails);
        }
        return listingDetails;
    }

}
