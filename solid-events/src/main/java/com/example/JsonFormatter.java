package com.example;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JsonFormatter implements Formatter {

    public String format(ArrayList<String> formatted) {
        Gson gson = new Gson();
        return gson.toJson(formatted);
    }

}
