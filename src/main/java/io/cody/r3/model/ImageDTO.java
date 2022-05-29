package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImageDTO {

    private Long id;

    @NotNull
    private Boolean isPrimary;

    @Size(max = 60)
    private String description;

    @NotNull
    @Size(max = 4000)
    private String image;

    private Long productImages;

    private Long vendorImages;

}
