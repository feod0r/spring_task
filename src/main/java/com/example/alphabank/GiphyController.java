package com.example.alphabank;

import com.example.alphabank.network.Giphy;
import com.example.alphabank.network.GiphyData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/giphy")
public class GiphyController {

    @Autowired
    private Giphy giphy;

    @Autowired
    private CurrencyController currencyController;

    @RequestMapping("/{coin}")
    public String realAct(@PathVariable String coin){
        float actual = Float.parseFloat(currencyController.realAct(coin));
        float older = Float.parseFloat(currencyController.oldValue(coin));
        
        String request = "";
        if(actual> older){
            request = "rich";
        } else if (actual != older){
            request = "broke";
        } else {
            request = "nothing";
        }

        GiphyData response = giphy.getGiphy("fFPW2olPpSAlXJf350D7Ba6uIheF0NpE",
                new Random().nextInt(1000),
                request);

        Map buf = (Map) response.getData().get("images");
        buf = (Map) buf.get("original");
        String url = (String) buf.get("url");

        String gif = "<html><head><title>"+ request +"</title></head><body><img src=\"" + url
                + "\"></body></html>";

        return gif;
    }
}
