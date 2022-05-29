package io.cody.r3.service;

import io.cody.r3.domain.Address;
import io.cody.r3.domain.Customer;
import io.cody.r3.domain.Info1099;
import io.cody.r3.domain.Purchase;
import io.cody.r3.domain.Vendor;
import io.cody.r3.model.AddressDTO;
import io.cody.r3.repos.AddressRepository;
import io.cody.r3.repos.CustomerRepository;
import io.cody.r3.repos.Info1099Repository;
import io.cody.r3.repos.PurchaseRepository;
import io.cody.r3.repos.VendorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final VendorRepository vendorRepository;
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final Info1099Repository info1099Repository;

    public AddressService(final AddressRepository addressRepository,
            final VendorRepository vendorRepository, final PurchaseRepository purchaseRepository,
            final CustomerRepository customerRepository,
            final Info1099Repository info1099Repository) {
        this.addressRepository = addressRepository;
        this.vendorRepository = vendorRepository;
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.info1099Repository = info1099Repository;
    }

    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapToDTO(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    public AddressDTO get(final Long id) {
        return addressRepository.findById(id)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }

    public void update(final Long id, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setId(address.getId());
        addressDTO.setAddressFor(address.getAddressFor());
        addressDTO.setIsPrimary(address.getIsPrimary());
        addressDTO.setName(address.getName());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setStreet2(address.getStreet2());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZip(address.getZip());
        addressDTO.setVendorAddresses(address.getVendorAddresses() == null ? null : address.getVendorAddresses().getId());
        addressDTO.setShippingAddress(address.getShippingAddress() == null ? null : address.getShippingAddress().getId());
        addressDTO.setBillingAddress(address.getBillingAddress() == null ? null : address.getBillingAddress().getId());
        addressDTO.setCustomerAddresses(address.getCustomerAddresses() == null ? null : address.getCustomerAddresses().getId());
        addressDTO.setHomeAddress(address.getHomeAddress() == null ? null : address.getHomeAddress().getId());
        addressDTO.setLocalShopAddress(address.getLocalShopAddress() == null ? null : address.getLocalShopAddress().getId());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setAddressFor(addressDTO.getAddressFor());
        address.setIsPrimary(addressDTO.getIsPrimary());
        address.setName(addressDTO.getName());
        address.setStreet(addressDTO.getStreet());
        address.setStreet2(addressDTO.getStreet2());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZip(addressDTO.getZip());
        if (addressDTO.getVendorAddresses() != null && (address.getVendorAddresses() == null || !address.getVendorAddresses().getId().equals(addressDTO.getVendorAddresses()))) {
            final Vendor vendorAddresses = vendorRepository.findById(addressDTO.getVendorAddresses())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "vendorAddresses not found"));
            address.setVendorAddresses(vendorAddresses);
        }
        if (addressDTO.getShippingAddress() != null && (address.getShippingAddress() == null || !address.getShippingAddress().getId().equals(addressDTO.getShippingAddress()))) {
            final Purchase shippingAddress = purchaseRepository.findById(addressDTO.getShippingAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "shippingAddress not found"));
            address.setShippingAddress(shippingAddress);
        }
        if (addressDTO.getBillingAddress() != null && (address.getBillingAddress() == null || !address.getBillingAddress().getId().equals(addressDTO.getBillingAddress()))) {
            final Purchase billingAddress = purchaseRepository.findById(addressDTO.getBillingAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "billingAddress not found"));
            address.setBillingAddress(billingAddress);
        }
        if (addressDTO.getCustomerAddresses() != null && (address.getCustomerAddresses() == null || !address.getCustomerAddresses().getId().equals(addressDTO.getCustomerAddresses()))) {
            final Customer customerAddresses = customerRepository.findById(addressDTO.getCustomerAddresses())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customerAddresses not found"));
            address.setCustomerAddresses(customerAddresses);
        }
        if (addressDTO.getHomeAddress() != null && (address.getHomeAddress() == null || !address.getHomeAddress().getId().equals(addressDTO.getHomeAddress()))) {
            final Info1099 homeAddress = info1099Repository.findById(addressDTO.getHomeAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "homeAddress not found"));
            address.setHomeAddress(homeAddress);
        }
        if (addressDTO.getLocalShopAddress() != null && (address.getLocalShopAddress() == null || !address.getLocalShopAddress().getId().equals(addressDTO.getLocalShopAddress()))) {
            final Vendor localShopAddress = vendorRepository.findById(addressDTO.getLocalShopAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "localShopAddress not found"));
            address.setLocalShopAddress(localShopAddress);
        }
        return address;
    }

}
