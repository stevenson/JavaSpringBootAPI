package com.stevenson.parcel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelRequest {

    @NotBlank(message = "Weight is required.")
    private double weight;
    @NotBlank(message = "height is required.")
    private double height;
    @NotBlank(message = "Width is required.")
    private double width;
    @NotBlank(message = "Length is required.")
    private double length;
}
