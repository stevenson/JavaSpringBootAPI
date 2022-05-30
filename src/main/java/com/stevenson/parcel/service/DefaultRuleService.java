package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.repo.RuleRepo;
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
public class DefaultRuleService implements RuleService{
    private final RuleRepo repo;

    @Autowired
    public DefaultRuleService(RuleRepo repo) {
        this.repo = repo;
    }

    @Override
    public Rule create(Rule rule) {
        return repo.save(rule);
    }

    @Override
    public List<Rule> retrieveAll() {
        return stream(repo.findAll().spliterator(), false).collect(toList());
    }

    public List<Rule> retrieveAll(Pageable paging) {
        return stream(repo.findAll(paging)
                .spliterator(), false)
                .collect(toList());
    }

    @Override
    public Optional<Rule> retrieve(long id) {
        return repo.findById(id);
    }
}
