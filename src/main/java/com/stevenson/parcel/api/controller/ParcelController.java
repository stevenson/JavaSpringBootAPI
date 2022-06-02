package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParcelController {
    ResponseEntity<?> addParcel(ParcelRequest request);

    ResponseEntity<List<?>> getAllParcel(
            Integer page, Integer pageSize, String sortBy, Sort.Direction direction);

    ResponseEntity<?> getParcel(long id);
}
