package com.stevenson.parcel.service;

import com.stevenson.parcel.model.Voucher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class DefaultVoucherService implements VoucherService{

    @Value("${services.voucher}")
    private String voucherServiceUrl;

    @Override
    public Optional<Voucher> retrieve(String key) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(voucherServiceUrl+key)
                .queryParam("key","apikey");
        System.out.println(builder.buildAndExpand().toUri());
        ResponseEntity<Voucher>  response = restTemplate.getForEntity(
                builder.buildAndExpand().toUri(),
                Voucher.class);
        System.out.println(response);
        Voucher voucher = response.getBody();

        return Optional.ofNullable(voucher);
    }
}
