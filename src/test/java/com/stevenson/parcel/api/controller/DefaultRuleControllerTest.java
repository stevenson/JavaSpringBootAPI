package com.stevenson.parcel.api.controller;

import com.stevenson.parcel.api.dto.ParcelResponse;
import com.stevenson.parcel.api.dto.RuleRequest;
import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.service.DefaultParcelService;
import com.stevenson.parcel.service.DefaultRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefaultRuleControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private DefaultRuleService mockRuleService;
    @Test
    void addRuleShouldSuccessfullyCreateARule() {
        RuleRequest request = new RuleRequest(
                1, "reject", "weight",
                "exceeds", 50, 0 ,"weight" );
        Rule rule1 = Rule.builder()
                .priority(1)
                .name("reject")
                .param("weight")
                .condition("exceeds")
                .threshold(50)
                .rate(0.0)
                .factor("weight")
                .build();
        given(this.mockRuleService.create(any())).willReturn(rule1);
        ResponseEntity<Rule> response = restTemplate.postForEntity(
                "http://localhost:"+ port + "/api/v1/rules",
                request,
                Rule.class);
        assertThat(response, is(notNullValue()));
        assertThat(response.getBody().getName(), is(rule1.getName()));
        assertThat(response.getStatusCodeValue(), is(201));
    }
}