package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Parcel;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ParcelService {
    Parcel create(Parcel parcel);
    List<Parcel> retrieveAll();
    List<Parcel> retrieveAll(Pageable paging);
    Optional<Parcel> retrieve(long id);
}
