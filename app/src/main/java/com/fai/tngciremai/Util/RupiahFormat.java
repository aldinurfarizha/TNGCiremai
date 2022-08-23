package com.fai.tngciremai.Util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RupiahFormat {
    public static String convert(Double number){
        DecimalFormat kursIdr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIdr.setDecimalFormatSymbols(formatRp);

        return kursIdr.format(number);
    }

}
