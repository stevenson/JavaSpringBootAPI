package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Parcel;
import com.stevenson.parcel.repo.ParcelRepo;
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
class ParcelServiceTest {

    @Mock
    private ParcelRepo repo;
    @Mock
    private RuleService ruleService;
    @Mock
    private VoucherService voucherService;
    private ParcelService service ;

    @BeforeEach
    void setup(){
        service = new DefaultParcelService(repo, ruleService, voucherService);
    }

    @Test
    void create() {
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
    void retrieveAll() {
    }

    @Test
    void testRetrieveAll() {
    }

    @Test
    void retrieve() {
    }
}