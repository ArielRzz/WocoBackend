package com.nocountry.woco.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotosRequest {
    @NotBlank
    @NotNull
    private String description;
    @NotBlank
    @NotNull
    private String url;
}
