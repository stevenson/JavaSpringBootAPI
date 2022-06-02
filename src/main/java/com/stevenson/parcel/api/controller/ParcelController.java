package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelResponse;
import com.stevenson.parcel.model.Parcel;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParcelController {
    ResponseEntity<?> addParcel(Parcel request);

    ResponseEntity<List<?>> getAllParcel(
            Integer page, Integer pageSize, String sortBy, Sort.Direction direction);

    ResponseEntity<?> getParcel(long id);
}
