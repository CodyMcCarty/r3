package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BankAccountDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nameOnAccount;

    @NotNull
    @Size(max = 255)
    private String accountType;

    @NotNull
    private Long routing;

    @NotNull
    private Long accountNum;

    @NotNull
    private Long vendorBankAccount;

}
