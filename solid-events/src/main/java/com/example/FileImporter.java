package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileImporter {

    public JsonArray readJsonFromFile() throws IOException {
        Gson gson = new Gson();
        URL url = this.getClass().getResource("/events.json");
        String raw = new String(Files.readAllBytes(Paths.get(url.getFile())));
        JsonElement element = gson.fromJson(raw, JsonElement.class);
        return element.getAsJsonArray();
    }

}
