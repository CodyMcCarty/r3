package io.cody.r3.rest;

import io.cody.r3.model.Info1099DTO;
import io.cody.r3.service.Info1099Service;
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
@RequestMapping(value = "/api/info1099s", produces = MediaType.APPLICATION_JSON_VALUE)
public class Info1099Resource {

    private final Info1099Service info1099Service;

    public Info1099Resource(final Info1099Service info1099Service) {
        this.info1099Service = info1099Service;
    }

    @GetMapping
    public ResponseEntity<List<Info1099DTO>> getAllInfo1099s() {
        return ResponseEntity.ok(info1099Service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Info1099DTO> getInfo1099(@PathVariable final Long id) {
        return ResponseEntity.ok(info1099Service.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createInfo1099(@RequestBody @Valid final Info1099DTO info1099DTO) {
        return new ResponseEntity<>(info1099Service.create(info1099DTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInfo1099(@PathVariable final Long id,
            @RequestBody @Valid final Info1099DTO info1099DTO) {
        info1099Service.update(id, info1099DTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInfo1099(@PathVariable final Long id) {
        info1099Service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
