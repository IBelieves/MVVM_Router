package cn.hualand.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonConverter<T> implements PropertyConverter<T, String> {
    @Override
    public T convertToEntityProperty(String databaseValue) {
        Type type = new TypeToken<T>() {
        }.getType();
        return new Gson().fromJson(databaseValue, type);
    }

    @Override
    public String convertToDatabaseValue(T entityProperty) {
        return new Gson().toJson(entityProperty);
    }
}