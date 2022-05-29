package io.cody.r3.service;

import io.cody.r3.domain.Purchase;
import io.cody.r3.domain.SaleHistory;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.SaleHistoryDTO;
import io.cody.r3.repos.PurchaseRepository;
import io.cody.r3.repos.SaleHistoryRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SaleHistoryService {

    private final SaleHistoryRepository saleHistoryRepository;
    private final VendorRepository vendorRepository;
    private final PurchaseRepository purchaseRepository;

    public SaleHistoryService(final SaleHistoryRepository saleHistoryRepository,
            final VendorRepository vendorRepository, final PurchaseRepository purchaseRepository) {
        this.saleHistoryRepository = saleHistoryRepository;
        this.vendorRepository = vendorRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<SaleHistoryDTO> findAll() {
        return saleHistoryRepository.findAll()
                .stream()
                .map(saleHistory -> mapToDTO(saleHistory, new SaleHistoryDTO()))
                .collect(Collectors.toList());
    }

    public SaleHistoryDTO get(final Long id) {
        return saleHistoryRepository.findById(id)
                .map(saleHistory -> mapToDTO(saleHistory, new SaleHistoryDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SaleHistoryDTO saleHistoryDTO) {
        final SaleHistory saleHistory = new SaleHistory();
        mapToEntity(saleHistoryDTO, saleHistory);
        return saleHistoryRepository.save(saleHistory).getId();
    }

    public void update(final Long id, final SaleHistoryDTO saleHistoryDTO) {
        final SaleHistory saleHistory = saleHistoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(saleHistoryDTO, saleHistory);
        saleHistoryRepository.save(saleHistory);
    }

    public void delete(final Long id) {
        saleHistoryRepository.deleteById(id);
    }

    private SaleHistoryDTO mapToDTO(final SaleHistory saleHistory,
            final SaleHistoryDTO saleHistoryDTO) {
        saleHistoryDTO.setId(saleHistory.getId());
        saleHistoryDTO.setIsFulfilled(saleHistory.getIsFulfilled());
        saleHistoryDTO.setIsInprogress(saleHistory.getIsInprogress());
        saleHistoryDTO.setTransactionFee(saleHistory.getTransactionFee());
        saleHistoryDTO.setPaymentProcessingFee(saleHistory.getPaymentProcessingFee());
        saleHistoryDTO.setVendorSalesHistory(saleHistory.getVendorSalesHistory() == null ? null : saleHistory.getVendorSalesHistory().getId());
        saleHistoryDTO.setPurchase(saleHistory.getPurchase() == null ? null : saleHistory.getPurchase().getId());
        return saleHistoryDTO;
    }

    private SaleHistory mapToEntity(final SaleHistoryDTO saleHistoryDTO,
            final SaleHistory saleHistory) {
        saleHistory.setIsFulfilled(saleHistoryDTO.getIsFulfilled());
        saleHistory.setIsInprogress(saleHistoryDTO.getIsInprogress());
        saleHistory.setTransactionFee(saleHistoryDTO.getTransactionFee());
        saleHistory.setPaymentProcessingFee(saleHistoryDTO.getPaymentProcessingFee());
        if (saleHistoryDTO.getVendorSalesHistory() != null && (saleHistory.getVendorSalesHistory() == null || !saleHistory.getVendorSalesHistory().getId().equals(saleHistoryDTO.getVendorSalesHistory()))) {
            final Vendor vendorSalesHistory = vendorRepository.findById(saleHistoryDTO.getVendorSalesHistory())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorSalesHistory not found"));
            saleHistory.setVendorSalesHistory(vendorSalesHistory);
        }
        if (saleHistoryDTO.getPurchase() != null && (saleHistory.getPurchase() == null || !saleHistory.getPurchase().getId().equals(saleHistoryDTO.getPurchase()))) {
            final Purchase purchase = purchaseRepository.findById(saleHistoryDTO.getPurchase())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "purchase not found"));
            saleHistory.setPurchase(purchase);
        }
        return saleHistory;
    }

}
