package io.cody.r3.rest;

import io.cody.r3.model.ListingDetailsDTO;
import io.cody.r3.service.ListingDetailsService;
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
@RequestMapping(value = "/api/listingDetailss", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListingDetailsResource {

    private final ListingDetailsService listingDetailsService;

    public ListingDetailsResource(final ListingDetailsService listingDetailsService) {
        this.listingDetailsService = listingDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<ListingDetailsDTO>> getAllListingDetailss() {
        return ResponseEntity.ok(listingDetailsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDetailsDTO> getListingDetails(@PathVariable final Long id) {
        return ResponseEntity.ok(listingDetailsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createListingDetails(
            @RequestBody @Valid final ListingDetailsDTO listingDetailsDTO) {
        return new ResponseEntity<>(listingDetailsService.create(listingDetailsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateListingDetails(@PathVariable final Long id,
            @RequestBody @Valid final ListingDetailsDTO listingDetailsDTO) {
        listingDetailsService.update(id, listingDetailsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteListingDetails(@PathVariable final Long id) {
        listingDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
