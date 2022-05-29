package io.cody.r3.service;

import io.cody.r3.domain.BankAccount;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.BankAccountDTO;
import io.cody.r3.repos.BankAccountRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final VendorRepository vendorRepository;

    public BankAccountService(final BankAccountRepository bankAccountRepository,
            final VendorRepository vendorRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<BankAccountDTO> findAll() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> mapToDTO(bankAccount, new BankAccountDTO()))
                .collect(Collectors.toList());
    }

    public BankAccountDTO get(final Long id) {
        return bankAccountRepository.findById(id)
                .map(bankAccount -> mapToDTO(bankAccount, new BankAccountDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final BankAccountDTO bankAccountDTO) {
        final BankAccount bankAccount = new BankAccount();
        mapToEntity(bankAccountDTO, bankAccount);
        return bankAccountRepository.save(bankAccount).getId();
    }

    public void update(final Long id, final BankAccountDTO bankAccountDTO) {
        final BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(bankAccountDTO, bankAccount);
        bankAccountRepository.save(bankAccount);
    }

    public void delete(final Long id) {
        bankAccountRepository.deleteById(id);
    }

    private BankAccountDTO mapToDTO(final BankAccount bankAccount,
            final BankAccountDTO bankAccountDTO) {
        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setNameOnAccount(bankAccount.getNameOnAccount());
        bankAccountDTO.setAccountType(bankAccount.getAccountType());
        bankAccountDTO.setRouting(bankAccount.getRouting());
        bankAccountDTO.setAccountNum(bankAccount.getAccountNum());
        bankAccountDTO.setVendorBankAccount(bankAccount.getVendorBankAccount() == null ? null : bankAccount.getVendorBankAccount().getId());
        return bankAccountDTO;
    }

    private BankAccount mapToEntity(final BankAccountDTO bankAccountDTO,
            final BankAccount bankAccount) {
        bankAccount.setNameOnAccount(bankAccountDTO.getNameOnAccount());
        bankAccount.setAccountType(bankAccountDTO.getAccountType());
        bankAccount.setRouting(bankAccountDTO.getRouting());
        bankAccount.setAccountNum(bankAccountDTO.getAccountNum());
        if (bankAccountDTO.getVendorBankAccount() != null && (bankAccount.getVendorBankAccount() == null || !bankAccount.getVendorBankAccount().getId().equals(bankAccountDTO.getVendorBankAccount()))) {
            final Vendor vendorBankAccount = vendorRepository.findById(bankAccountDTO.getVendorBankAccount())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorBankAccount not found"));
            bankAccount.setVendorBankAccount(vendorBankAccount);
        }
        return bankAccount;
    }

}
