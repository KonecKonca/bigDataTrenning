package com.epam.kozitski.domain.util;

import com.epam.kozitski.domain.Country;

public class Countries {
    public static final long DEFAULT_COUNTRY_ID = 1;
    public static final String DEFAULT_COUNTRY_NAME = "Belarus";

    public static Country createDefaultCountry(){
        return new Country(DEFAULT_COUNTRY_ID, DEFAULT_COUNTRY_NAME);
    }

}
