package net.laymanmu.bentbranch.events;

import com.google.gson.Gson;

import java.util.HashMap;

public class Publisher {
    public enum Topic {
        Metrics
    }

    private final static HashMap<Topic,Event> events = new HashMap<>();

    public final static Gson gson = new Gson();

    public static class Payload {
        public final Topic topic;
        public final Event event;
        public final String eventType;
        public final long timestamp;

        public Payload(Topic topic, Event event) {
            this.topic = topic;
            this.event = event;
            this.eventType = event.getClass().getSimpleName();
            this.timestamp = System.currentTimeMillis();
        }

        public String toJson() {
            return gson.toJson(this);
        }
    }

    public static void publish(Topic topic, Event event) {
        System.out.println(new Payload(topic, event).toJson());
    }
}
