package com.example;

import com.example.db.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventProcessorTest {

    @Autowired
    EventRepository repo;

    @Test
    public void returnsFormattedEventsFromJson() throws IOException {
        EventProcessor processor = new EventProcessor(repo, new HTMLFormatter(), new FileImporter(), new EventFormatter());
        assertThat(processor.doStuff()).isEqualTo("<table>\n" +
                "<tr>\n" +
                "  <td>\n" +
                "    firsties signed up with first@example.com  </td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "  <td>\n" +
                "    A new business was created: Acme  </td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "  <td>\n" +
                "    Business 12 was renamed from Acme to Widget Co:  </td>\n" +
                "</tr>\n" +
                "</table>");
    }

    @Test
    public void returnsJsonFormattedEventsFromJson() throws IOException {
        EventProcessor processor = new EventProcessor(repo, new HTMLFormatter(), new FileImporter(), new EventFormatter());
        assertThat(processor.doStuff()).isEqualTo("[" +
                "\"firsties signed up with first@example.com\"," +
                "\"A new business was created: Acme\"," +
                "\"Business 12 was renamed from Acme to Widget Co:\"" +
                "]");
    }

    // @Test
    // public void returnsFormattedEventsFromDb() throws IOException {
    //     EventProcessor processor = new EventProcessor(repo, new HTMLFormatter(), new FileImporter(), new EventFormatter(), new JsonFormatter());
    //     assertThat(processor.eventsToTableFromDb()).isEqualTo("<table>\n" +
    //             "<tr>\n" +
    //             "  <td>\n" +
    //             "    firsties signed up with first@example.com  </td>\n" +
    //             "</tr>\n" +
    //             "<tr>\n" +
    //             "  <td>\n" +
    //             "    A new business was created: Acme  </td>\n" +
    //             "</tr>\n" +
    //             "<tr>\n" +
    //             "  <td>\n" +
    //             "    Business 12 was renamed from Acme to Widget Co:  </td>\n" +
    //             "</tr>\n" +
    //             "</table>");
    // }

}
