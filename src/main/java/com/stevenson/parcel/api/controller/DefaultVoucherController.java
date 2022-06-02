package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("api/v1/vouchers")
@RestController
public class DefaultVoucherController implements VoucherController{

    private final VoucherService service;
    @Autowired
    public DefaultVoucherController(VoucherService service){
        this.service = service;
    }
    @Override
    @GetMapping(path="{code}")
    public ResponseEntity<?> getVoucher(@PathVariable("code") String code) {
        Optional<?> voucher = service.retrieve(code);
        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }
}
