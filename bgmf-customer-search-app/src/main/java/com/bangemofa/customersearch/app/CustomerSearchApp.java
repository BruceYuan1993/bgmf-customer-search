package com.bangemofa.customersearch.app;


import com.bangemofa.customersearch.api.SearchUnitProvider;
import com.bangemofa.customersearch.api.support.ExcelSearchUnitProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomerSearchApp {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSearchApp.class);
    public static void main(String[] args) throws FileNotFoundException {
        SearchUnitProvider provider = new ExcelSearchUnitProvider(
                new FileInputStream("/home/bruceyuan/IdeaProjects/bgmf/bgmf-customer-search/bgmf-customer-search-api-baidumap/src/main/resources/baidu_city.xlsx"));
        System.out.println(provider.get());
    }
}
