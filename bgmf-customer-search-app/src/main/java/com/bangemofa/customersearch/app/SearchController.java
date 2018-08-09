//package com.bangemofa.customersearch.app;
//
//import java.net.URLEncoder;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//
//@RestController
//public class SearchController {
//    //@Autowired
//    private RestTemplate template;
//
//    private String baseUrl = "http://api.map.baidu.com/place/v2/search";
//
//    private String ak = "2w6LP75zGETvjEutkXdm4KUUDUY4Gp9N";
//
//    @GetMapping("/search")
//    public String init() {
//        String query = URLEncoder.encode("幼儿园");
//        String region = URLEncoder.encode("延津县");
//        String fullUrl = baseUrl +   + "query=" + query + "&region="+region + "&output=json&ak="+ ak;
//        return fullUrl;
//        //template.get
//    }
//}
