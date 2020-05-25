package net.laymanmu.bentbranch.events;

public class Event {
    public void publish() {
        publish(Publisher.Topic.Metrics);
    }
    public void publish(Publisher.Topic topic) {
        Publisher.publish(topic, this);
    }
}
