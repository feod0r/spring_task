package com.example.alphabank;

import com.example.alphabank.network.Currency;
import com.example.alphabank.network.ExRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private Currency currency;

    @Value("${openex.apiKey}")
    private String apiKey;

    @Value("${openex.base}")
    private String base;

    @RequestMapping("/getActual")
    public String actual(){
        return "Акуальный курс валют";
    }

    float currencyFromMap(Map<String, Float> map, String coin){
        if(!map.isEmpty()) {
            if (map.containsKey(coin)) {
                return map.get(coin);
            } else {
                return 0;
            }
        }
        return 0;
    }

    @RequestMapping("/realValue/{coin}")
    public String realAct(@PathVariable String coin){
         ExRate response = currency.getActual(apiKey,base);

        return String.valueOf(currencyFromMap(response.getRates(),coin));
    }

    @RequestMapping("/oldValue/{coin}")
    public String oldValue(@PathVariable String coin){
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));

        String dataFormatted = new SimpleDateFormat("yyyy-MM-dd").format(date);
        ExRate response = currency.getPastDay(apiKey, base, dataFormatted);

        return String.valueOf(currencyFromMap(response.getRates(),coin));
    }
}
