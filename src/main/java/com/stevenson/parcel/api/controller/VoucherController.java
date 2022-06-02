package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.RuleRequest;
import org.springframework.http.ResponseEntity;

public interface VoucherController {
    ResponseEntity<?> getVoucher(String code);
}
