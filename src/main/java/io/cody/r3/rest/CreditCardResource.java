package io.cody.r3.rest;

import io.cody.r3.model.CreditCardDTO;
import io.cody.r3.service.CreditCardService;
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
@RequestMapping(value = "/api/creditCards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardResource {

    private final CreditCardService creditCardService;

    public CreditCardResource(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        return ResponseEntity.ok(creditCardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDTO> getCreditCard(@PathVariable final Long id) {
        return ResponseEntity.ok(creditCardService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCreditCard(
            @RequestBody @Valid final CreditCardDTO creditCardDTO) {
        return new ResponseEntity<>(creditCardService.create(creditCardDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCreditCard(@PathVariable final Long id,
            @RequestBody @Valid final CreditCardDTO creditCardDTO) {
        creditCardService.update(id, creditCardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable final Long id) {
        creditCardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
