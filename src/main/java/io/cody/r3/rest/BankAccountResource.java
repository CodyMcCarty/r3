package io.cody.r3.rest;

import io.cody.r3.model.BankAccountDTO;
import io.cody.r3.service.BankAccountService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/bankAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankAccountResource {

    private final BankAccountService bankAccountService;

    public BankAccountResource(final BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccounts() {
        return ResponseEntity.ok(bankAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccount(@PathVariable final Long id) {
        return ResponseEntity.ok(bankAccountService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBankAccount(
            @RequestBody @Valid final BankAccountDTO bankAccountDTO) {
        return new ResponseEntity<>(bankAccountService.create(bankAccountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBankAccount(@PathVariable final Long id,
            @RequestBody @Valid final BankAccountDTO bankAccountDTO) {
        bankAccountService.update(id, bankAccountDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable final Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
