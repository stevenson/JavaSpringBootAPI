package com.stevenson.parcel.repo;

import com.stevenson.parcel.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepo  extends JpaRepository<Parcel, Long> {
}

