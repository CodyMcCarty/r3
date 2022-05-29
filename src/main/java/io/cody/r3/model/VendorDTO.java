package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VendorDTO {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String vendorName;

    @Size(max = 60)
    private String headLine;

    @NotNull
    @Size(max = 255)
    private String vendorEmail;

    @Size(max = 255)
    private String phoneNumber;

    @NotNull
    private Boolean localOnly;

    @Size(max = 1000)
    private String about;

    @Size(max = 255)
    private String promoVideoLink;

    @Size(max = 255)
    private String webLinks;

    @Size(max = 255)
    private String policies;

    @NotNull
    private Boolean isVendorActive;

}
