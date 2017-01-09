package com.example;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventFormatter {

    public void formatEvent(ArrayList<String> formatted, JsonObject jsonElement) {
        JsonObject event = jsonElement;
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


}
