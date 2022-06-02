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
            Rule rejectRule = Rule.builder()
                    .priority(1)
                    .name("reject")
                    .param("weight")
                    .condition("exceeds")
                    .threshold(50)
                    .rate(0.0)
                    .factor("weight")
                    .build();
            repo.save(rejectRule);
            log.info("Created rule: "+rejectRule.getName());
            Rule heavyParcelRule = Rule.builder()
                    .priority(2)
                    .name("heavy parcel")
                    .param("weight")
                    .condition("exceeds")
                    .threshold(10)
                    .rate(20.0)
                    .factor("weight")
                    .build();
            repo.save(heavyParcelRule);
            log.info("Created rule: "+heavyParcelRule.getName());
            Rule smallParcelRule = Rule.builder()
                    .priority(3)
                    .name("small parcel")
                    .param("volume")
                    .condition("below")
                    .threshold(1500)
                    .rate(0.03)
                    .factor("volume")
                    .build();
            repo.save(smallParcelRule);
            log.info("Created rule: "+smallParcelRule.getName());
            Rule mediumParcelRule = Rule.builder()
                    .priority(4)
                    .name("small parcel")
                    .param("volume")
                    .condition("below")
                    .threshold(2500)
                    .rate(0.04)
                    .factor("volume")
                    .build();
            repo.save(mediumParcelRule);
            log.info("Created rule: "+mediumParcelRule.getName());
            Rule largeParcelRule = Rule.builder()
                    .priority(4)
                    .name("small parcel")
                    .param("volume")
                    .condition("exceeds")
                    .threshold(2499)
                    .rate(0.05)
                    .factor("volume")
                    .build();
            repo.save(largeParcelRule);
            log.info("Created rule: "+largeParcelRule.getName());
        }
        //TODO: add other rules
        log.info("- rule count = "+repo.count());
    }
}
