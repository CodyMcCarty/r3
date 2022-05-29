package io.cody.r3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListingDetailsDTO {

    private Long id;

    @NotNull
    private WhoMade whoMade;

    @NotNull
    private Boolean isHandMade;

    @NotNull
    private Boolean isAutoRenewed;

    @Size(max = 255)
    @JsonProperty("sKU")
    private String sKU;

    @NotNull
    private Boolean hasColors;

    @NotNull
    private Boolean hasSecondaryColor;

    @NotNull
    private Boolean hasSizes;

    @NotNull
    private Boolean hasMaterials;

    @NotNull
    private Boolean hasGenders;

    @NotNull
    private Boolean hasPersonalization;

    @NotNull
    private Boolean hasOtherfield;

    @Size(max = 255)
    private String colors;

    @Size(max = 255)
    private String sizes;

    @Size(max = 255)
    private String material;

    @Size(max = 255)
    private String genders;

    @Size(max = 255)
    private String secondaryColor;

    @Size(max = 255)
    private String personalization;

    @Size(max = 255)
    private String otherfields;

    @NotNull
    private Long productDetails;

}
