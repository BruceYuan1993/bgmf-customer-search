package com.bangemofa.customersearch.api;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String address;
    private String province;
    private String city;
    private String area;
    private String telephone;
}
