package com.stevenson.parcel.dataseeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.repo.RuleRepo;

@Component
public class RuleDataSeeder implements CommandLineRunner {

    @Autowired
    RuleRepo repo;

    @Override
    public void run(String... args) throws Exception {
        loadRuleData();
    }

    private void loadRuleData() {
        System.out.println("Running seeder");
        if (repo.count() == 0) {
            Rule rule1 = Rule.builder()
                    .priority(1)
                    .name("reject")
                    .param("weight")
                    .condition("exceeds")
                    .threshold(50)
                    .rate(0.0)
                    .factor("weight")
                    .build();
            repo.save(rule1);
            System.out.println("Created rule: "+rule1.getName());
        }
        System.out.println(repo.count());
    }
}
