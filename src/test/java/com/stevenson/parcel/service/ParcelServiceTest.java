package com.stevenson.parcel.service;

import com.stevenson.parcel.api.controller.exception.ApiRejectException;
import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.model.Rule;
import com.stevenson.parcel.model.Voucher;
import com.stevenson.parcel.repo.ParcelRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ParcelServiceTest {


    @Mock
    private RuleService ruleService;
    @Mock
    private VoucherService voucherService;
    private ParcelService service ;

    @Captor
    private ArgumentCaptor<Parcel> parcelCaptor;

    @BeforeEach
    void setup(){

    }

    @Test
    void createShouldCallAndPassSaveTheRightParcel() {
        ParcelRepo repo = Mockito.mock(ParcelRepo.class);
        service = new DefaultParcelService(repo, ruleService, voucherService);
        double weight = 10, length = 3, width = 3, height = 3;
        Parcel actual = Parcel.builder()
                .weight(weight)
                .length(length)
                .width(width)
                .height(height)
                .build();
        service.create(actual);
        ArgumentCaptor<Parcel> argumentCaptor = ArgumentCaptor.forClass(Parcel.class);
        verify(repo, times(1)).save(argumentCaptor.capture());
        Parcel captured = argumentCaptor.getValue();
        assertThat(captured).isEqualTo(actual);
    }

    @Test
    void createShouldConsiderRules() {
        //given
        ParcelRepo repo = Mockito.mock(ParcelRepo.class);
        service = new DefaultParcelService(repo, ruleService, voucherService);
        double weight = 100, length = 3, width = 3, height = 3;
        Parcel parcel = Parcel.builder()
                .weight(weight)
                .length(length)
                .width(width)
                .height(height)
                .build();
        Rule rejectRule = Rule.builder()
                .priority(1)
                .name("reject")
                .param("weight")
                .condition("exceeds")
                .threshold(50)
                .rate(0.0)
                .factor("weight")
                .build();
        List<Rule> rules = new ArrayList<>();
        rules.add(rejectRule);
        when(ruleService.retrieveAll(any())).thenReturn(rules);

        // when
        ApiRejectException exception = assertThrows(ApiRejectException.class, ()  -> {
            service.create(parcel);
        });

        // assertion
        assertThat(exception.getMessage(), is("Parcel cost not computed Rejection due to rule."));
    }

    @Test
    void createShouldConsiderVouchers() {
        //given
        ParcelRepo repo = Mockito.mock(ParcelRepo.class);
        service = new DefaultParcelService(repo, ruleService, voucherService);
        double weight = 10, length = 4, width = 3, height = 100;
        double discount = 10;
        double expectedVolume = length * width * height;
        double expectedCost = (expectedVolume * 0.4)  - discount ;
        Parcel parcel = Parcel.builder()
                .weight(weight)
                .length(length)
                .width(width)
                .height(height)
                .voucherCode("MYTN")
                .build();
        Voucher voucher = Voucher.builder()
                .code("MYTN")
                .discount(discount)
                .expiry(LocalDate.now().plusDays(2))
                .build();
        when(ruleService.retrieveAll(any())).thenReturn(Collections.emptyList());
        when(voucherService.retrieve(any())).thenReturn(Optional.ofNullable(voucher));

        // when
        service.create(parcel);

        // assertion
        verify(repo, times(1)).save(parcelCaptor.capture());
        Parcel parcelBeforeSaving = parcelCaptor.getValue();
        assertThat(parcelBeforeSaving.getVolume(), is(expectedVolume));
        assertThat(parcelBeforeSaving.getCost(), is(expectedCost));
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