package io.cody.r3.rest;

import io.cody.r3.model.PromoDTO;
import io.cody.r3.service.PromoService;
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
@RequestMapping(value = "/api/promos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PromoResource {

    private final PromoService promoService;

    public PromoResource(final PromoService promoService) {
        this.promoService = promoService;
    }

    @GetMapping
    public ResponseEntity<List<PromoDTO>> getAllPromos() {
        return ResponseEntity.ok(promoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromoDTO> getPromo(@PathVariable final Long id) {
        return ResponseEntity.ok(promoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPromo(@RequestBody @Valid final PromoDTO promoDTO) {
        return new ResponseEntity<>(promoService.create(promoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePromo(@PathVariable final Long id,
            @RequestBody @Valid final PromoDTO promoDTO) {
        promoService.update(id, promoDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePromo(@PathVariable final Long id) {
        promoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
