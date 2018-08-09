package com.bangemofa.customersearch.api.baidumap;

import lombok.Data;

@Data
public class BaiduMapSearchResult {
    private int status;
    private String message;
    private int total;
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
    
    
    
    
    @Data
    public static class Location{
        private float lat;
        private float lng;
    }

    @Data
    public static class DetailInfo{
        private int distance;
        private String city;
        private String type;
        private String tag;
        private NaviLocation navi_location;
        
        private Object children;
        
        private String uid;
        private String name;
        private String show_name;
        //private String tag;
        private Object location;
        
        
        @Data
        public static class NaviLocation{
            private float lat;
            private float lng;
            private String detail_url;
        }
    }
}


