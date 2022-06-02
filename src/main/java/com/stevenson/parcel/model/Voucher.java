package com.stevenson.parcel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {
    private String code;
    private double discount;
    private LocalDate expiry;

    public boolean applies(){
        return expiry.isAfter( LocalDate.now());
    }
}
