package com.uvic.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utility {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static Date getNDaysAgoDate(int days){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().minusDays(days);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }

    public static Double formatDecimalToPrecision2(Double input){
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
