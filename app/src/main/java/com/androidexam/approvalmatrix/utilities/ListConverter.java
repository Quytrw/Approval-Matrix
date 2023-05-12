package com.androidexam.approvalmatrix.utilities;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ListConverter {

    @TypeConverter
    public static List<List<String>> jsonToListString(String value)
    {
        return new Gson().fromJson(value, new TypeToken<List<List<String>>>(){}.getType());
    }

    @TypeConverter
    public static String listStringToJson(List<List<String>> value)
    {
        return new Gson().toJson(value);
    }
}
