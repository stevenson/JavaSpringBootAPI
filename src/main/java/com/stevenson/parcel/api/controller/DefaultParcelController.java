package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelRequest;
import com.stevenson.parcel.api.dto.ParcelResponse;
import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.service.ParcelService;
import com.stevenson.parcel.service.RuleService;
import com.stevenson.parcel.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/parcels")
@RestController
public class DefaultParcelController implements ParcelController {

    private final ParcelService parcelService;
    private final RuleService ruleService;
    private final VoucherService voucherService;

    @Autowired
    public DefaultParcelController(ParcelService service, RuleService ruleService, VoucherService voucherService) {
        this.parcelService = service;
        this.ruleService = ruleService;
        this.voucherService = voucherService;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addParcel(@Valid @NonNull @RequestBody ParcelRequest request) {
        Parcel parcel = parcelService.create(Parcel.builder()
                .weight(request.getWeight())
                .length(request.getLength())
                .width(request.getWidth())
                .height(request.getHeight())
                .voucherCode(request.getVoucherCode())
                .build());

        ParcelResponse data = new ParcelResponse(parcel);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<?>> getAllParcel(Integer page, Integer pageSize, String sortBy, Sort.Direction direction) {
        return null;
    }

    @Override
    public ResponseEntity<?> getParcel(long id) {
        return null;
    }
}
