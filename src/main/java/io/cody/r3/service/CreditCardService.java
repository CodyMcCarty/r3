package io.cody.r3.service;

import io.cody.r3.domain.CreditCard;
import io.cody.r3.domain.Customer;
import io.cody.r3.domain.Purchase;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.CreditCardDTO;
import io.cody.r3.repos.CreditCardRepository;
import io.cody.r3.repos.CustomerRepository;
import io.cody.r3.repos.PurchaseRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public CreditCardService(final CreditCardRepository creditCardRepository,
            final PurchaseRepository purchaseRepository,
            final CustomerRepository customerRepository, final VendorRepository vendorRepository) {
        this.creditCardRepository = creditCardRepository;
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<CreditCardDTO> findAll() {
        return creditCardRepository.findAll()
                .stream()
                .map(creditCard -> mapToDTO(creditCard, new CreditCardDTO()))
                .collect(Collectors.toList());
    }

    public CreditCardDTO get(final Long id) {
        return creditCardRepository.findById(id)
                .map(creditCard -> mapToDTO(creditCard, new CreditCardDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CreditCardDTO creditCardDTO) {
        final CreditCard creditCard = new CreditCard();
        mapToEntity(creditCardDTO, creditCard);
        return creditCardRepository.save(creditCard).getId();
    }

    public void update(final Long id, final CreditCardDTO creditCardDTO) {
        final CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(creditCardDTO, creditCard);
        creditCardRepository.save(creditCard);
    }

    public void delete(final Long id) {
        creditCardRepository.deleteById(id);
    }

    private CreditCardDTO mapToDTO(final CreditCard creditCard, final CreditCardDTO creditCardDTO) {
        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setIsPrimary(creditCard.getIsPrimary());
        creditCardDTO.setCardholder(creditCard.getCardholder());
        creditCardDTO.setCardNumber(creditCard.getCardNumber());
        creditCardDTO.setExpiration(creditCard.getExpiration());
        creditCardDTO.setCvv(creditCard.getCvv());
        creditCardDTO.setZip(creditCard.getZip());
        creditCardDTO.setPruchaseCreditCard(creditCard.getPruchaseCreditCard() == null ? null : creditCard.getPruchaseCreditCard().getId());
        creditCardDTO.setCustomerCreditCards(creditCard.getCustomerCreditCards() == null ? null : creditCard.getCustomerCreditCards().getId());
        creditCardDTO.setVendorCreditCard(creditCard.getVendorCreditCard() == null ? null : creditCard.getVendorCreditCard().getId());
        return creditCardDTO;
    }

    private CreditCard mapToEntity(final CreditCardDTO creditCardDTO, final CreditCard creditCard) {
        creditCard.setIsPrimary(creditCardDTO.getIsPrimary());
        creditCard.setCardholder(creditCardDTO.getCardholder());
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setExpiration(creditCardDTO.getExpiration());
        creditCard.setCvv(creditCardDTO.getCvv());
        creditCard.setZip(creditCardDTO.getZip());
        if (creditCardDTO.getPruchaseCreditCard() != null && (creditCard.getPruchaseCreditCard() == null || !creditCard.getPruchaseCreditCard().getId().equals(creditCardDTO.getPruchaseCreditCard()))) {
            final Purchase pruchaseCreditCard = purchaseRepository.findById(creditCardDTO.getPruchaseCreditCard())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "pruchaseCreditCard not found"));
            creditCard.setPruchaseCreditCard(pruchaseCreditCard);
        }
        if (creditCardDTO.getCustomerCreditCards() != null && (creditCard.getCustomerCreditCards() == null || !creditCard.getCustomerCreditCards().getId().equals(creditCardDTO.getCustomerCreditCards()))) {
            final Customer customerCreditCards = customerRepository.findById(creditCardDTO.getCustomerCreditCards())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customerCreditCards not found"));
            creditCard.setCustomerCreditCards(customerCreditCards);
        }
        if (creditCardDTO.getVendorCreditCard() != null && (creditCard.getVendorCreditCard() == null || !creditCard.getVendorCreditCard().getId().equals(creditCardDTO.getVendorCreditCard()))) {
            final Vendor vendorCreditCard = vendorRepository.findById(creditCardDTO.getVendorCreditCard())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorCreditCard not found"));
            creditCard.setVendorCreditCard(vendorCreditCard);
        }
        return creditCard;
    }

}
