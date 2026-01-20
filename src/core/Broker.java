package core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Represents a message broker in the messaging system
// Core service component that handles message storage and retrieval
public class Broker {
    private final String brokerId;
    private final String host;
    private final int port;
    private final Map<String, Topic> topics;
    private final ReadWriteLock topicsLock;
    private final AtomicBoolean running;
    private final Map<String, Object> brokerConfig;

    public Broker(String brokerId, String host, int port) {
        this.brokerId = brokerId;
        this.host = host;
        this.port = port;

        this.topics = new ConcurrentHashMap<>();
        this.topicsLock = new ReentrantReadWriteLock();
        this.running = new AtomicBoolean(false);
        this.brokerConfig = new HashMap<>();

        //Default configurations can be set here
        brokerConfig.put("max.message.size", 1024 * 1024); //1MB
        brokerConfig.put("log.retention.hours", 168); //7 days
        brokerConfig.put("log.segment.bytes", 1024 * 1024 * 1024); //1GB
    }

    //start broker
    //stop broker
    //create a new topic
    //delete a topic
    //get topic by name
    //get all topics
    //produce a message to a topic
    //consume messages from a topic partition
    //get broker metadata
}
