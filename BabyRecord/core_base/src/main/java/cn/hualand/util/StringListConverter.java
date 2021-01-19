package cn.hualand.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IntegerListConverter implements PropertyConverter<ArrayList<Integer>, String> {
    @Override
    public ArrayList<Integer> convertToEntityProperty(String databaseValue) {
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(databaseValue,type);
    }

    @Override
    public String convertToDatabaseValue(ArrayList<Integer> entityProperty) {
        return new Gson().toJson(entityProperty);
    }
}