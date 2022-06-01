package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.RuleRequest;
import com.stevenson.parcel.model.Rule;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RuleController {

    ResponseEntity<?> addRule(RuleRequest request);
}
