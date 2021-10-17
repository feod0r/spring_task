package com.example.alphabank.network;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "currency", url = "${openex.url}")
public interface Currency {
//    @RequestLine("GET latest.json?app_id={appID}&base={base}")
    @RequestMapping(value = "latest.json?app_id={appID}&base={base}",method = RequestMethod.GET)
    ExRate getActual(@PathVariable String appID, @PathVariable String base);

    @RequestMapping(value = "historical/{date}.json?app_id={appID}&base={base}", method = RequestMethod.GET)
    ExRate getPastDay(@PathVariable String appID, @PathVariable String base, @PathVariable String date);
}
