package io.cody.r3.rest;

import io.cody.r3.model.SaleHistoryDTO;
import io.cody.r3.service.SaleHistoryService;
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
@RequestMapping(value = "/api/saleHistorys", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleHistoryResource {

    private final SaleHistoryService saleHistoryService;

    public SaleHistoryResource(final SaleHistoryService saleHistoryService) {
        this.saleHistoryService = saleHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<SaleHistoryDTO>> getAllSaleHistorys() {
        return ResponseEntity.ok(saleHistoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleHistoryDTO> getSaleHistory(@PathVariable final Long id) {
        return ResponseEntity.ok(saleHistoryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSaleHistory(
            @RequestBody @Valid final SaleHistoryDTO saleHistoryDTO) {
        return new ResponseEntity<>(saleHistoryService.create(saleHistoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSaleHistory(@PathVariable final Long id,
            @RequestBody @Valid final SaleHistoryDTO saleHistoryDTO) {
        saleHistoryService.update(id, saleHistoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSaleHistory(@PathVariable final Long id) {
        saleHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
