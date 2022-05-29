package io.cody.r3.rest;

import io.cody.r3.model.StaffDTO;
import io.cody.r3.service.StaffService;
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
@RequestMapping(value = "/api/staffs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StaffResource {

    private final StaffService staffService;

    public StaffResource(final StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaffs() {
        return ResponseEntity.ok(staffService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable final Long id) {
        return ResponseEntity.ok(staffService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createStaff(@RequestBody @Valid final StaffDTO staffDTO) {
        return new ResponseEntity<>(staffService.create(staffDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStaff(@PathVariable final Long id,
            @RequestBody @Valid final StaffDTO staffDTO) {
        staffService.update(id, staffDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteStaff(@PathVariable final Long id) {
        staffService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
