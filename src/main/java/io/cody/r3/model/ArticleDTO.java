package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticleDTO {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String title;

    @Size(max = 255)
    private String links;

    @NotNull
    @Size(max = 1000)
    private String body;

}
