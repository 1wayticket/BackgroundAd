package com.quzubuluo.quzulock.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    public static final String TAG = CustomGsonResponseBodyConverter.class.getSimpleName();

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String oldString = value.string();
        try {
            JSONObject jsonObject = new JSONObject(oldString);
            String code = jsonObject.getString("result");
            String msg = jsonObject.optString("msg");
            if (code.equals("0") || code.equals("400")) {
                JsonReader jsonReader = gson.newJsonReader(new StringReader(oldString));
                try {
                    return adapter.read(jsonReader);
                } finally {
                    value.close();
                }
            } else {
                throw new ExceptionHandle.ServerException(Integer.valueOf(code), msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new ExceptionHandle.ServerException(ExceptionHandle.ERROR.UNKNOWN, "");
    }
}
