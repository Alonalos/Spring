package com.example.cybertek.interfaces.carpetPrices;

import com.example.cybertek.enums.City;

import java.math.BigDecimal;

public interface Carpet {
    BigDecimal getSqFtPrice (City city);
}
