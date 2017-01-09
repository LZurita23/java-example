package com.example;

import com.example.db.Event;
import com.example.db.EventRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DatabaseImporter {
    private final EventRepository db;

    DatabaseImporter(EventRepository db) {
        this.db = db;
    }

    public ArrayList<JsonElement> getFromDb() {
        Gson gson = new Gson();
        Iterable<Event> eventRecords = db.findAll();
        ArrayList<JsonElement> events = new ArrayList<>();
        for(Event event : eventRecords) {
            JsonObject json = gson.fromJson(event.getAttributes(), JsonObject.class);
            json.addProperty("type", event.getType());
            events.add(json);
        }
        return events;
    }

}
