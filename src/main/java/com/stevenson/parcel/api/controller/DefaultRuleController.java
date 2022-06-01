package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.RuleRequest;
import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("api/v1/rules")
@RestController
public class DefaultRuleController implements RuleController{
    private final RuleService ruleService;

    @Autowired
    public DefaultRuleController(RuleService ruleService) {
         this.ruleService = ruleService;
    }

    @PostMapping
    @Override
    public ResponseEntity<?> addRule(@Valid @NonNull @RequestBody RuleRequest request) {
        Rule data = ruleService.create(Rule.builder()
                .priority(request.getPriority())
                .name(request.getName())
                .param(request.getParam())
                .condition(request.getCondition())
                .threshold(request.getThreshold())
                .rate(request.getRate())
                .factor(request.getFactor())
                .build());
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
