package com.example.expenses;

import androidx.room.TypeConverter;

public class BooleanConverter {

    @TypeConverter
    public boolean toBoolean(int value){
        return value == 1;
    }

    @TypeConverter
    public int toInt(boolean value){
        return value ? 1: 0;
    }
}
