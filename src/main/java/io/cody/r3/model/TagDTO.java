package io.cody.r3.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TagDTO {

    private Long id;

    @Size(max = 255)
    private String tag;

    private Long productTags;

    private Long articleTag;

}
