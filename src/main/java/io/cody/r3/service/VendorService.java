package io.cody.r3.service;

import io.cody.r3.domain.Vendor;
import io.cody.r3.model.VendorDTO;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(final VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<VendorDTO> findAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> mapToDTO(vendor, new VendorDTO()))
                .collect(Collectors.toList());
    }

    public VendorDTO get(final Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> mapToDTO(vendor, new VendorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VendorDTO vendorDTO) {
        final Vendor vendor = new Vendor();
        mapToEntity(vendorDTO, vendor);
        return vendorRepository.save(vendor).getId();
    }

    public void update(final Long id, final VendorDTO vendorDTO) {
        final Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(vendorDTO, vendor);
        vendorRepository.save(vendor);
    }

    public void delete(final Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO mapToDTO(final Vendor vendor, final VendorDTO vendorDTO) {
        vendorDTO.setId(vendor.getId());
        vendorDTO.setVendorName(vendor.getVendorName());
        vendorDTO.setHeadLine(vendor.getHeadLine());
        vendorDTO.setVendorEmail(vendor.getVendorEmail());
        vendorDTO.setPhoneNumber(vendor.getPhoneNumber());
        vendorDTO.setLocalOnly(vendor.getLocalOnly());
        vendorDTO.setAbout(vendor.getAbout());
        vendorDTO.setPromoVideoLink(vendor.getPromoVideoLink());
        vendorDTO.setWebLinks(vendor.getWebLinks());
        vendorDTO.setPolicies(vendor.getPolicies());
        vendorDTO.setIsVendorActive(vendor.getIsVendorActive());
        return vendorDTO;
    }

    private Vendor mapToEntity(final VendorDTO vendorDTO, final Vendor vendor) {
        vendor.setVendorName(vendorDTO.getVendorName());
        vendor.setHeadLine(vendorDTO.getHeadLine());
        vendor.setVendorEmail(vendorDTO.getVendorEmail());
        vendor.setPhoneNumber(vendorDTO.getPhoneNumber());
        vendor.setLocalOnly(vendorDTO.getLocalOnly());
        vendor.setAbout(vendorDTO.getAbout());
        vendor.setPromoVideoLink(vendorDTO.getPromoVideoLink());
        vendor.setWebLinks(vendorDTO.getWebLinks());
        vendor.setPolicies(vendorDTO.getPolicies());
        vendor.setIsVendorActive(vendorDTO.getIsVendorActive());
        return vendor;
    }

}
