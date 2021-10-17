package com.example.alphabank;

import com.example.alphabank.network.Currency;
import com.example.alphabank.network.ExRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.LinkedCaseInsensitiveMap;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;


@SpringBootTest
class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;

    @MockBean
    private Currency currency;

    @Test
    void currencyFromMap() {
        Map<String,Float> test = new LinkedCaseInsensitiveMap<>();
        test.put("USD", (float)1);
        test.put("RUB", (float)Float.MAX_VALUE);
        Map<String,Float> empty = new LinkedCaseInsensitiveMap<>();

        Assertions.assertEquals(0,currencyController.currencyFromMap(test, "NotACoin"));
        Assertions.assertEquals(1,currencyController.currencyFromMap(test, "USD"));
        Assertions.assertEquals(0,currencyController.currencyFromMap(empty, "USD"));
        Assertions.assertEquals(Float.MAX_VALUE,currencyController.currencyFromMap(test, "RUB"));
    }

    @Test
    void realAct() {
        Map<String,Float> simpleMap = new LinkedCaseInsensitiveMap<>();
        simpleMap.put("USD", (float)1);
        simpleMap.put("RUB", (float)1337);

        ExRate exRate  = new ExRate("disc","licence",1234,"USD", simpleMap);
        Mockito.doReturn(exRate).when(currency).getActual("9a7f3c4e61b44c3d86192057328d8b0a","USD");
        Assertions.assertEquals(String.valueOf((float)1337),currencyController.realAct("RUB"));
        Assertions.assertEquals(String.valueOf((float)0),currencyController.realAct("Easy"));
    }

    @Test
    void oldValue() {
        Map<String,Float> simpleMap = new LinkedCaseInsensitiveMap<>();
        simpleMap.put("USD", (float)1);
        simpleMap.put("RUB", (float)1337);

        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
        String dataFormatted = new SimpleDateFormat("yyyy-MM-dd").format(date);

        ExRate exRate  = new ExRate("disc","licence",1234,"USD", simpleMap);
        Mockito.doReturn(exRate).when(currency).getPastDay("9a7f3c4e61b44c3d86192057328d8b0a","USD", dataFormatted);
        Assertions.assertEquals(String.valueOf((float)1337),currencyController.oldValue("RUB"));
        Assertions.assertEquals(String.valueOf((float)0),currencyController.oldValue("Easy"));
    }
}