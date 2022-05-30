package com.stevenson.parcel.repo;

import com.stevenson.parcel.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepo extends JpaRepository<Rule, Long> {
}

