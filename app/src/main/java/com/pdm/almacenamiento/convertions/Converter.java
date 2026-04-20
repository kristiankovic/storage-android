package com.pdm.almacenamiento.convertions;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {

    // conversor de string a fecha y viceversa
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @TypeConverter
    public static String fromDate(Date date){
        return date == null ? null : FORMAT.format(date);
    }

    @TypeConverter
    public static Date fromDate(String date){
        try {
            return date == null ? null : FORMAT.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
