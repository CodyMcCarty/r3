package io.cody.r3.service;

import io.cody.r3.domain.Info1099;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.Info1099DTO;
import io.cody.r3.repos.Info1099Repository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class Info1099Service {

    private final Info1099Repository info1099Repository;
    private final VendorRepository vendorRepository;

    public Info1099Service(final Info1099Repository info1099Repository,
            final VendorRepository vendorRepository) {
        this.info1099Repository = info1099Repository;
        this.vendorRepository = vendorRepository;
    }

    public List<Info1099DTO> findAll() {
        return info1099Repository.findAll()
                .stream()
                .map(info1099 -> mapToDTO(info1099, new Info1099DTO()))
                .collect(Collectors.toList());
    }

    public Info1099DTO get(final Long id) {
        return info1099Repository.findById(id)
                .map(info1099 -> mapToDTO(info1099, new Info1099DTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Info1099DTO info1099DTO) {
        final Info1099 info1099 = new Info1099();
        mapToEntity(info1099DTO, info1099);
        return info1099Repository.save(info1099).getId();
    }

    public void update(final Long id, final Info1099DTO info1099DTO) {
        final Info1099 info1099 = info1099Repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(info1099DTO, info1099);
        info1099Repository.save(info1099);
    }

    public void delete(final Long id) {
        info1099Repository.deleteById(id);
    }

    private Info1099DTO mapToDTO(final Info1099 info1099, final Info1099DTO info1099DTO) {
        info1099DTO.setId(info1099.getId());
        info1099DTO.setDateOfBirth(info1099.getDateOfBirth());
        info1099DTO.setTIN(info1099.getTIN());
        info1099DTO.setVendor1099(info1099.getVendor1099() == null ? null : info1099.getVendor1099().getId());
        return info1099DTO;
    }

    private Info1099 mapToEntity(final Info1099DTO info1099DTO, final Info1099 info1099) {
        info1099.setDateOfBirth(info1099DTO.getDateOfBirth());
        info1099.setTIN(info1099DTO.getTIN());
        if (info1099DTO.getVendor1099() != null && (info1099.getVendor1099() == null || !info1099.getVendor1099().getId().equals(info1099DTO.getVendor1099()))) {
            final Vendor vendor1099 = vendorRepository.findById(info1099DTO.getVendor1099())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendor1099 not found"));
            info1099.setVendor1099(vendor1099);
        }
        return info1099;
    }

}
