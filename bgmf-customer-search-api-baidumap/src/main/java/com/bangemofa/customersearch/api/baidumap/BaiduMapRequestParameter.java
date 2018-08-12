package com.bangemofa.customersearch.api.baidumap;

import lombok.Data;

/**
 * Created by bruceyuan on 18-8-11.
 */
@Data
public class BaiduMapRequestParameter {
    private String query;
    private String tag;
    private String region;
    private boolean city_limit = true;
    private String output = "json";
    private String scope;
    private String filter;
    private int coord_type = 3;
    private String ret_coordtype;
    private int page_size = 20;
    private int page_num;
    private String ak;
    private String sn;
    private String timestamp;
}
