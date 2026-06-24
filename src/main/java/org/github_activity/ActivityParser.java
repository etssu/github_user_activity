package org.github_activity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class ActivityParser {
    private Map<String,Integer> pushCount;


    public void parseText(String text) {
        pushCount = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();

        try {
            JsonNode events = objectMapper.readTree(text);
            if (!events.isArray() || events.isEmpty()) {
                System.err.println("No activity found.");
            }

            for (JsonNode event : events) {
                String type = event.path("type").asText();

                switch (type) {
                    case "PushEvent":
                        parsePushEvent(event);
                        break;
                    case "IssuesEvent":
                        sb.append(parseIssueEvent(event)).append("\n");
                        break;
                    case "WatchEvent":
                        sb.append(parseWatchEvent(event)).append("\n");
                        break;
                    default:
                        System.out.println("- Performed " + type + " on " + event.get("repo").get("name").asText());
                        break;
                }
            }
            for (Map.Entry<String, Integer> entry : pushCount.entrySet()) {
                sb.append("- Pushed ")
                        .append(entry.getValue())
                        .append(" times to ")
                        .append(entry.getKey())
                        .append("\n");
            }
            System.out.println(sb);

        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
            System.err.println("Error while parsing JSON.");
        }
    }

    private void parsePushEvent(JsonNode event) {
        String repo = event.get("repo").get("name").asText(); // key
        pushCount.put(repo, pushCount.getOrDefault(repo, 0) + 1);
    }

    private String parseIssueEvent(JsonNode event) {
        String repo = event.get("repo").get("name").asText();
        return "- Opened a new issue in " + repo;
    }

    private String parseWatchEvent(JsonNode event) {
        String repo = event.get("repo").get("name").asText();
        return "- Starred " + repo;
    }
}
