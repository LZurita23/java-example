package com.example;

import com.example.db.Event;
import com.example.db.EventRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Component
class EventProcessor {

    private final EventRepository db;
    private final Formatter outputFormatter;
    private final FileImporter fileImporter;
    private final EventFormatter eventFormatter;

    EventProcessor(EventRepository db, Formatter outputFormatter, FileImporter fileImporter, EventFormatter eventFormatter) {
        this.db = db;
        this.fileImporter = fileImporter;
        this.eventFormatter = eventFormatter;
        this.outputFormatter = outputFormatter;
    }

    String doStuff() throws IOException {
        JsonArray events = this.fileImporter.readJsonFromFile();

        ArrayList<String> formatted = new ArrayList<>();
        for (JsonElement jsonElement : events) {
            this.eventFormatter.formatEvent(formatted, (JsonObject) jsonElement);
        }
        return this.outputFormatter.format(formatted);
    }

    // String eventsToTableFromDb() throws IOException {
    //     ArrayList<JsonElement> events = new DatabaseImporter(this.db).getFromDb();
    //     ArrayList<String> formatted = new ArrayList<>();
    //     for (JsonElement jsonElement : events) {
    //         this.eventFormatter.formatEvent(formatted, (JsonObject) jsonElement);
    //     }
    //     return this.htmlFormatter.makeTable(formatted);
    // }
}