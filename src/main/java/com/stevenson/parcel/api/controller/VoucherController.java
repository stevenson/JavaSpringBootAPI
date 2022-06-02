package com.stevenson.parcel.api.controller;

import org.springframework.http.ResponseEntity;

public interface VoucherController {
    ResponseEntity<?> getVoucher(String code);
}
