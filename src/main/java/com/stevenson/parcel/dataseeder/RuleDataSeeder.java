package com.stevenson.parcel.dataseeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.repo.RuleRepo;

@Component
@Slf4j
public class RuleDataSeeder implements CommandLineRunner {

    @Autowired
    RuleRepo repo;

    @Override
    public void run(String... args) {
        loadRuleData();
    }

    private void loadRuleData() {
        log.info("Running seeder: Creating 5 base rules");
        if (repo.count() == 0) {
            Rule overweightRule = Rule.builder()
                    .priority(1)
                    .name("reject")
                    .param("weight")
                    .condition("exceeds")
                    .threshold(50)
                    .rate(0.0)
                    .factor("weight")
                    .build();
            repo.save(overweightRule);
            log.info("Created rule: "+overweightRule.getName());
        }
        //TODO: add other rules
        log.info("- rule count = "+repo.count());
    }
}
