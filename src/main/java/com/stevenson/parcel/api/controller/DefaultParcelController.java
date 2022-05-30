package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelResponse;
import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.service.ParcelService;
import com.stevenson.parcel.service.RuleService;
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

@RequestMapping("api/v1/parcel")
@RestController
public class DefaultParcelController implements ParcelController {

    private final ParcelService parcelService;
    private final RuleService ruleService;

    public DefaultParcelController(ParcelService service, RuleService ruleService) {
        this.parcelService = service;
        this.ruleService = ruleService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ParcelResponse> addParcel(@Valid @NonNull @RequestBody Parcel request) {

        Parcel parcel = parcelService.create(Parcel.builder()
                        .length(request.getLength())
                        .width(request.getWidth())
                        .height(request.getHeight())
                        .build());
        Pageable paging = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "priority"));
        List<Rule> rules = ruleService.retrieveAll(paging);
        for(Rule rule: rules){
            if(rule.applies(parcel)){
                parcel.getCost(rule);
                break;
            }
        }
        ParcelResponse data = new ParcelResponse(parcel);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ParcelResponse>> getAllParcel(Integer page, Integer pageSize, String sortBy, Sort.Direction direction) {
        return null;
    }

    @Override
    public ResponseEntity<ParcelResponse> getParcel(long id) {
        return null;
    }
}
