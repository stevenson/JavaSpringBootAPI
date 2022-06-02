package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.repo.RuleRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RuleServiceTest {

    @Mock
    private RuleRepo repo;

    private RuleService service ;

    @BeforeEach
    void setup(){
        service = new DefaultRuleService(repo);
    }

    @Test
    void create() {
        Rule actual = Rule.builder()
                .priority(1)
                .name("reject")
                .param("weight")
                .condition("exceeds")
                .threshold(50)
                .rate(0.0)
                .factor("weight")
                .build();
        service.create(actual);
        ArgumentCaptor<Rule> argumentCaptor = ArgumentCaptor.forClass(Rule.class);
        verify(repo, times(1)).save(argumentCaptor.capture());
        Rule captured = argumentCaptor.getValue();
        assertThat(captured).isEqualTo(actual);
    }

    @Test
    void retrieveAll() {
    }

    @Test
    void testRetrieveAll() {
    }

    @Test
    void retrieve() {
    }
}