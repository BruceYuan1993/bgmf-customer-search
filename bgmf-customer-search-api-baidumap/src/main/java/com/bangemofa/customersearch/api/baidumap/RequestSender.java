package com.bangemofa.customersearch.api.baidumap;

import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * Created by bruceyuan on 18-8-11.
 */
public interface RequestSender {
    @RequestLine("GET")
    BaiduMapSearchResult send(@QueryMap BaiduMapRequestParameter param);

    //
}
