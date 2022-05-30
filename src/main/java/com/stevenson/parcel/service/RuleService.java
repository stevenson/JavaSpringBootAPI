package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Rule;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RuleService {
    Rule create(Rule parcel);
    List<Rule> retrieveAll();
    List<Rule> retrieveAll(Pageable paging);
    Optional<Rule> retrieve(long id);
}
