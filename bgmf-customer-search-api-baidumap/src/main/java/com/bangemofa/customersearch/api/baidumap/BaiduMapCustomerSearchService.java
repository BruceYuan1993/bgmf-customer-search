package com.bangemofa.customersearch.api.baidumap;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bangemofa.customersearch.api.Customer;
import com.bangemofa.customersearch.api.CustomerSearchService;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.yaml.snakeyaml.Yaml;

//日配额（次)2,000  分钟并发数（次/分钟） 120
public class BaiduMapCustomerSearchService implements CustomerSearchService{
    private BaiduMapConfig config;
    private RequestSender sender;
    private RateLimiter limiter;
    public BaiduMapCustomerSearchService() {
        Yaml yaml = new Yaml();
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("BaiduMapConfig.yml");
        config = yaml.loadAs(configStream, BaiduMapConfig.class);

        sender = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)

                .target(RequestSender.class, config.getUrl());

        limiter = RateLimiter.create(2);
    }

    @SneakyThrows
    public List<Customer> search(String area) {
        List<Customer> custormers = new ArrayList<Customer>();
        BaiduMapRequestParameter parameter = new BaiduMapRequestParameter();
        // 百度API要求所有参数必须经过URLencode
        // Feign 的@QueryMap会对URL中的参数做URLencode，不需要再次做
        parameter.setQuery("幼儿园");
        parameter.setRegion(area);
        parameter.setAk(config.getAk());

        BaiduMapSearchResult result = null;

        while (result == null
                || (result.getTotal() / parameter.getPage_size() >= parameter.getPage_num())) {
            limiter.acquire();
            result = sender.send(parameter);
            System.out.println("total --->" + result.getTotal());
            System.out.println("pag_num --->" + parameter.getPage_num());
            Iterator<BaiduMapSearchResult.Item> resultIterator = result.getResults().iterator();
            while (resultIterator.hasNext()) {
                BaiduMapSearchResult.Item item = resultIterator.next();
                System.out.println(item);
                String tel = item.getTelephone();
                if (!Strings.isNullOrEmpty(tel) && tel.startsWith("1")){
                    Customer customer = new Customer();
                    BeanUtils.copyProperties(customer, item);
                    custormers.add(customer);
                }

            }

            parameter.setPage_num(parameter.getPage_num()+1);
        }
        System.out.println("size --->" + custormers.size());
        System.out.println(custormers);
        return custormers;
    }

}
