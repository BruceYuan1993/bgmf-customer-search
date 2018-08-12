package com.bangemofa.customersearch.api.baidumap;

import lombok.Data;

import java.util.List;
/*

{
    "status":0,
    "message":"ok",
    "total":48,
    "results":[
        {
            "name":"西小街幼儿园",
            "location":{
                "lat":35.146362,
                "lng":114.211302
            },
            "address":"城关镇东大街",
            "province":"河南省",
            "city":"新乡市",
            "area":"延津县",
            "telephone":"13017535505",
            "detail":1,
            "uid":"0259c27703532d12b9df8fa4"
        }
    ]
}


pag_num超出时：
{
    "status":0,
    "message":"ok",
    "total":0,
    "results":[

    ]
}
 */

@Data
public class BaiduMapSearchResult {
    private int status;
    private String message;
    private int total;
    private List<Item> results;
    
    
    
    @Data
    public static class Item{
        private String name;
        private Location location;


        private String address;
        private String province;
        private String city;
        private String area;
        private String telephone;
        private String uid;
        private String street_id;
        private String detail;
    }

    @Data
    public static class Location{
        private float lat;
        private float lng;
    }

//    @Data
//    public static class DetailInfo{
//        private int distance;
//        private String city;
//        private String type;
//        private String tag;
//        private NaviLocation navi_location;
//
//        private Object children;
//
//        private String uid;
//        private String name;
//        private String show_name;
//        //private String tag;
//        private Object location;
//
//
//        @Data
//        public static class NaviLocation{
//            private float lat;
//            private float lng;
//            private String detail_url;
//        }
//    }
}


