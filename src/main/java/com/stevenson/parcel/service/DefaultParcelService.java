package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.repo.ParcelRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
@Slf4j
public class DefaultParcelService implements ParcelService{
    private final ParcelRepo repo;

    @Autowired
    public DefaultParcelService(ParcelRepo repo) {
        this.repo = repo;
    }

    @Override
    public Parcel create(Parcel paymentHistory) {
        return repo.save(paymentHistory);
    }

    @Override
    public List<Parcel> retrieveAll() {
        return stream(repo.findAll().spliterator(), false).collect(toList());
    }

    public List<Parcel> retrieveAll(Pageable paging) {
        return stream(repo.findAll(paging)
                .spliterator(), false)
                .collect(toList());
    }

    @Override
    public Optional<Parcel> retrieve(long id) {
        return repo.findById(id);
    }
}
