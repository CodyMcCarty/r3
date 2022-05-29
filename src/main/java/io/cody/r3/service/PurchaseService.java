package io.cody.r3.service;

import io.cody.r3.domain.Customer;
import io.cody.r3.domain.Purchase;
import io.cody.r3.model.PurchaseDTO;
import io.cody.r3.repos.CustomerRepository;
import io.cody.r3.repos.PurchaseRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;

    public PurchaseService(final PurchaseRepository purchaseRepository,
            final CustomerRepository customerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
    }

    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll()
                .stream()
                .map(purchase -> mapToDTO(purchase, new PurchaseDTO()))
                .collect(Collectors.toList());
    }

    public PurchaseDTO get(final Long id) {
        return purchaseRepository.findById(id)
                .map(purchase -> mapToDTO(purchase, new PurchaseDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final PurchaseDTO purchaseDTO) {
        final Purchase purchase = new Purchase();
        mapToEntity(purchaseDTO, purchase);
        return purchaseRepository.save(purchase).getId();
    }

    public void update(final Long id, final PurchaseDTO purchaseDTO) {
        final Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(purchaseDTO, purchase);
        purchaseRepository.save(purchase);
    }

    public void delete(final Long id) {
        purchaseRepository.deleteById(id);
    }

    private PurchaseDTO mapToDTO(final Purchase purchase, final PurchaseDTO purchaseDTO) {
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseHistory(purchase.getPurchaseHistory() == null ? null : purchase.getPurchaseHistory().getId());
        return purchaseDTO;
    }

    private Purchase mapToEntity(final PurchaseDTO purchaseDTO, final Purchase purchase) {
        if (purchaseDTO.getPurchaseHistory() != null && (purchase.getPurchaseHistory() == null || !purchase.getPurchaseHistory().getId().equals(purchaseDTO.getPurchaseHistory()))) {
            final Customer purchaseHistory = customerRepository.findById(purchaseDTO.getPurchaseHistory())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "purchaseHistory not found"));
            purchase.setPurchaseHistory(purchaseHistory);
        }
        return purchase;
    }

}
