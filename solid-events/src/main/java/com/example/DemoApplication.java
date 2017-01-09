package com.example;

import com.example.db.Event;
import com.example.db.EventRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class);
    }

    @Component
    class Application implements CommandLineRunner {

        private final EventRepository eventsRepo;
        private final EventProcessor processor;

        Application(EventRepository eventsRepo, EventProcessor processor) {
            this.eventsRepo = eventsRepo;
            this.processor = processor;
        }

        @Override
        public void run(String... args) throws Exception {
            loadFixtures();
            // System.out.println(this.processor.eventsToTableFromJson());
            // System.out.println("-----------------------------------");
            // System.out.println(this.processor.eventsToTableFromDb());
        }

        private void loadFixtures() {
            Gson builder = new GsonBuilder().create();
            Event event1 = createFirstEvent(builder);
            Event event2 = createSecondEvent(builder);
            Event event3 = createThirdEvent(builder);

            this.eventsRepo.save(Arrays.asList(event1, event2, event3));
        }

        private Event createFirstEvent(Gson builder) {
            Event event = new Event();
            event.setType("USER_ADDED");
            JsonObject attributes = new JsonObject();
            attributes.addProperty("username", "firsties");
            attributes.addProperty("email", "first@example.com");
            event.setAttributes(builder.toJson(attributes));
            return event;
        }

        private Event createSecondEvent(Gson builder) {
            JsonObject attributes;
            Event event = new Event();
            event.setType("BUSINESS_ADDED");
            attributes = new JsonObject();
            attributes.addProperty("name", "Acme");
            event.setAttributes(builder.toJson(attributes));
            return event;
        }

        private Event createThirdEvent(Gson builder) {
            JsonObject attributes;
            Event event = new Event();
            event.setType("BUSINESS_RENAMED");
            attributes = new JsonObject();
            attributes.addProperty("id", 12);
            attributes.addProperty("oldName", "Acme");
            attributes.addProperty("newName", "Widget Co");
            event.setAttributes(builder.toJson(attributes));
            return event;
        }

    }

}
