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

    EventProcessor(EventRepository db) {
        this.db = db;
    }

    String eventsToTableFromJson() throws IOException {
        URL url = this.getClass().getResource("/events.json");
        String raw = new String(Files.readAllBytes(Paths.get(url.getFile())));
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(raw, JsonElement.class);
        JsonArray events = element.getAsJsonArray();
        ArrayList<String> formatted = new ArrayList<>();
        for (JsonElement jsonElement : events) {
            JsonObject event = (JsonObject) jsonElement;
            switch (event.get("type").getAsString()) {
                case "USER_ADDED":
                    String email = event.get("email").getAsString();
                    String username = event.get("username").getAsString();
                    formatted.add(String.format("%s signed up with %s", username, email));
                    break;
                case "BUSINESS_ADDED":
                    String name = event.get("name").getAsString();
                    event.addProperty("formatted", String.format("A new business was created: %s", name));
                    formatted.add(event.get("formatted").getAsString());
                    break;
                case "BUSINESS_RENAMED":
                    int id = event.get("id").getAsInt();
                    String oldName = event.get("oldName").getAsString();
                    String newName = event.get("newName").getAsString();
                    formatted.add(String.format("Business %d was renamed from %s to %s:", id, oldName, newName));
                    break;
            }
            ;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<table>\n");
        for (String message : formatted) {
            builder.append("<tr>\n")
                    .append("  <td>\n")
                    .append("    ").append(message)
                    .append("  </td>\n")
                    .append("</tr>\n");
        }
        builder.append("</table>");
        return builder.toString();
    }

    String eventsFromJsonToJson() throws IOException {
        URL url = this.getClass().getResource("/events.json");
        String raw = new String(Files.readAllBytes(Paths.get(url.getFile())));
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(raw, JsonElement.class);
        JsonArray events = element.getAsJsonArray();
        ArrayList<String> formatted = new ArrayList<>();
        for (JsonElement jsonElement : events) {
            JsonObject event = (JsonObject) jsonElement;
            switch (event.get("type").getAsString()) {
                case "USER_ADDED":
                    String email = event.get("email").getAsString();
                    String username = event.get("username").getAsString();
                    formatted.add(String.format("%s signed up with %s", username, email));
                    break;
                case "BUSINESS_ADDED":
                    String name = event.get("name").getAsString();
                    event.addProperty("formatted", String.format("A new business was created: %s", name));
                    formatted.add(event.get("formatted").getAsString());
                    break;
                case "BUSINESS_RENAMED":
                    int id = event.get("id").getAsInt();
                    String oldName = event.get("oldName").getAsString();
                    String newName = event.get("newName").getAsString();
                    formatted.add(String.format("Business %d was renamed from %s to %s:", id, oldName, newName));
                    break;
            }
            ;
        }
        return gson.toJson(formatted);
    }

    String eventsToTableFromDb() throws IOException {
        Gson gson = new Gson();
        Iterable<Event> eventRecords = db.findAll();
        ArrayList<JsonElement> events = new ArrayList<>();
        for(Event event : eventRecords) {
            JsonObject json = gson.fromJson(event.getAttributes(), JsonObject.class);
            json.addProperty("type", event.getType());
            events.add(json);
        }
        ArrayList<String> formatted = new ArrayList<>();
        for (JsonElement jsonElement : events) {
            JsonObject event = (JsonObject) jsonElement;
            switch (event.get("type").getAsString()) {
                case "USER_ADDED":
                    String email = event.get("email").getAsString();
                    String username = event.get("username").getAsString();
                    formatted.add(String.format("%s signed up with %s", username, email));
                    break;
                case "BUSINESS_ADDED":
                    String name = event.get("name").getAsString();
                    event.addProperty("formatted", String.format("A new business was created: %s", name));
                    formatted.add(event.get("formatted").getAsString());
                    break;
                case "BUSINESS_RENAMED":
                    int id = event.get("id").getAsInt();
                    String oldName = event.get("oldName").getAsString();
                    String newName = event.get("newName").getAsString();
                    formatted.add(String.format("Business %d was renamed from %s to %s:", id, oldName, newName));
                    break;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<table>\n");
        for (String message : formatted) {
            builder.append("<tr>\n")
                    .append("  <td>\n")
                    .append("    ").append(message)
                    .append("  </td>\n")
                    .append("</tr>\n");
        }
        builder.append("</table>");
        return builder.toString();
    }

}