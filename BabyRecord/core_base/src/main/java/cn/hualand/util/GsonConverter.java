package cn.hualand.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StringListConverter implements PropertyConverter<ArrayList<String>, String> {
    @Override
    public ArrayList<String> convertToEntityProperty(String databaseValue) {
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(databaseValue,type);
    }

    @Override
    public String convertToDatabaseValue(ArrayList<String> entityProperty) {
        return new Gson().toJson(entityProperty);
    }
}