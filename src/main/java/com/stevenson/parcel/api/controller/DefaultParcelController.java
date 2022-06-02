package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelRequest;
import com.stevenson.parcel.api.dto.ParcelResponse;
import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.model.Voucher;
import com.stevenson.parcel.service.DefaultVoucherService;
import com.stevenson.parcel.service.ParcelService;
import com.stevenson.parcel.service.RuleService;
import com.stevenson.parcel.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Optional;

@RequestMapping("api/v1/parcels")
@RestController
public class DefaultParcelController implements ParcelController {

    private final ParcelService parcelService;
    private final RuleService ruleService;
    private final VoucherService voucherService;

    @Autowired
    public DefaultParcelController(ParcelService service, RuleService ruleService, VoucherService voucherService, VoucherService voucherService1) {
        this.parcelService = service;
        this.ruleService = ruleService;
        this.voucherService = voucherService1;
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addParcel(@Valid @NonNull @RequestBody ParcelRequest request) {

        Parcel parcel = parcelService.create(Parcel.builder()
                        .weight(request.getWeight())
                        .length(request.getLength())
                        .width(request.getWidth())
                        .height(request.getHeight())
                        .build());
        Pageable paging = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "priority"));
        List<Rule> rules = ruleService.retrieveAll(paging);
        for(Rule rule: rules){
            if(rule.applies(parcel)){
                parcel.applyRule(rule);
                break;
            }
        }
        System.out.println("voucherCode: "+ request.getVoucherCode());
        if(request.getVoucherCode()!= null ){
            Optional<Voucher> optional = voucherService.retrieve(request.getVoucherCode());
            if(!optional.isEmpty() && optional.get().applies()){
                parcel.applyVoucher(optional.get());
            }
        }
        ParcelResponse data = new ParcelResponse(parcel);
        HttpStatus responseStatus = HttpStatus.CREATED;
        if(data.getStatus() == "rejected"){
            responseStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(data, responseStatus);
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
