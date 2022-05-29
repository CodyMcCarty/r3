package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long id;

    @NotNull
    private AddressFor addressFor;

    @NotNull
    private Boolean isPrimary;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String street2;

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    private USStates state;

    @NotNull
    @Size(max = 255)
    private String zip;

    @NotNull
    private Long vendorAddresses;

    @NotNull
    private Long shippingAddress;

    @NotNull
    private Long billingAddress;

    private Long customerAddresses;

    private Long homeAddress;

    private Long localShopAddress;

}
