package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDTO {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String firstName;

    @NotNull
    @Size(max = 60)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String favShops;

    @Size(max = 255)
    private String favProducts;

    @NotNull
    private Boolean isCustomerActive;

}
