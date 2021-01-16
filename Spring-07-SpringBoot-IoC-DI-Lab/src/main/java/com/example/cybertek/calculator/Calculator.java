package com.example.cybertek.calculator;

import com.example.cybertek.enums.City;
import com.example.cybertek.interfaces.carpetPrices.Carpet;
import com.example.cybertek.interfaces.floorTypes.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Calculator {

    @Qualifier("carpetVA")
    @Autowired
    private Carpet carpet;

    @Qualifier("bedroom")
    @Autowired
    private Floor floor;

    public String getTotalCarpetCost(City city) throws Exception {
        BigDecimal cost = carpet.getSqFtPrice(city).multiply(floor.getArea());

        if (cost.compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("This city doesnt exist");
        }

        return "Total cost for Carpet: " + cost;
    }
}
