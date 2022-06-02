package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Voucher;

import java.util.Optional;

public interface VoucherService {
    Optional<Voucher> retrieve(String key);
}
