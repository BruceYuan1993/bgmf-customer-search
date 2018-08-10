package com.bangemofa.customersearch.api;

import java.util.List;

/**
 * Created by bruceyuan on 18-8-8.
 */
public interface SearchUnitProvider {
    //获取城市列表
    List<SearchUnit> get();
}
