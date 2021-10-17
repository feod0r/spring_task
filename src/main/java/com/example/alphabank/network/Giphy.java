package com.example.alphabank.network;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "giphy", url = "${giphy.url}")
public interface Giphy {
    @RequestMapping(value = "?api_key={apiKey}&q={query}&limit=1&offset={offset}&bundle=messaging_non_clips", method = RequestMethod.GET)
    GiphyData getGiphy(@PathVariable String apiKey, @PathVariable Integer offset, @PathVariable String query);

}
