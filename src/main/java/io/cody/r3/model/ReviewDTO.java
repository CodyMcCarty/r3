package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewDTO {

    private Long id;

    @NotNull
    private Double rating;

    @Size(max = 60)
    private String title;

    @Size(max = 255)
    private String body;

    private Long vendorReviews;

    private Long productReviews;

}
