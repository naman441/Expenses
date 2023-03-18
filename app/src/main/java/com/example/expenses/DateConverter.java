package com.example.expenses;

import androidx.room.TypeConverter;
import java.util.Date;

public class DateConverter {

    @TypeConverter
    public Date fromTimestamp(long value){
        return new Date(value);
    }

    @TypeConverter
    public long toTimestamp(Date date){
        return date.getTime();
    }
}
