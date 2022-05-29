package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditCardDTO {

    private Long id;

    @NotNull
    private Boolean isPrimary;

    @NotNull
    @Size(max = 255)
    private String cardholder;

    @NotNull
    @Size(max = 255)
    private String cardNumber;

    @NotNull
    @Size(max = 255)
    private String expiration;

    @NotNull
    @Size(max = 5)
    private String cvv;

    @Size(max = 255)
    private String zip;

    @NotNull
    private Long pruchaseCreditCard;

    private Long customerCreditCards;

    @NotNull
    private Long vendorCreditCard;

}
