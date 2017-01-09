package com.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HTMLFormatter implements Formatter {

    public String format(ArrayList<String> formatted) {
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
