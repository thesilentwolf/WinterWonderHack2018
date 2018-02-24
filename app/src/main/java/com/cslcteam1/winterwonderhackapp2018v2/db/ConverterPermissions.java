package com.cslcteam1.winterwonderhackapp2018v2.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Alexander Larkin on 2/24/2018.
 */

public class ConverterPermissions {

    @TypeConverter
    public static ArrayList<String> fromString(String permsJson){
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(permsJson, type);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> permsList){
        return new Gson().toJson(permsList);
    }

}
