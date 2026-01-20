package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Math.min;

public class Partition {
    private final String topicName;
    private final int partitionId;
    private final List<Message> messages;
    private final ReadWriteLock lock;

    private long nextOffset;
    private boolean isLeader;
    private Set<String> replicas;

    public Partition(String topicName, int partitionId) {
        this.topicName = topicName;
        this.partitionId = partitionId;
        this.messages = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
        this.nextOffset = 0;
        this.isLeader = true;
        this.replicas = ConcurrentHashMap.newKeySet(); //thread safe set backed by concurrent hash map
    }

    // Append a message to the partition
    public long appendMessage(Message message) {
        lock.writeLock().lock();
        try {
            message.setOffset(nextOffset);
            messages.add(message);
            return nextOffset++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Read messages from a given offset
    public List<Message> readMessages(long offset, int maxMessages) {
        lock.readLock().lock();
        try {
            if (offset < 0 || offset >= nextOffset) {
                return new ArrayList<>();
            }
            int startIndex = (int) offset;
            int endIndex = min(startIndex + maxMessages, messages.size());
            return new ArrayList<>(messages.subList(startIndex, endIndex));
        } finally {
            lock.readLock().unlock();
        }
    }

    // Get a message at a specific offset
    public Message getMessage(long offset) {
        lock.readLock().lock();
        try {
            if (offset < 0 || offset >= nextOffset) {
                return null;
            } else {
                return messages.get((int) offset);
            }
        } finally {
            lock.readLock().unlock();
        }

    }

    // Get the high watermark (next offset)
    public long getHighWatermark() {
        lock.readLock().lock();
        try {
            return nextOffset;
        } finally {
            lock.readLock().unlock();
        }
    }
    // Size of the partition
    public int size(){
        lock.readLock().lock();
        try {
            return messages.size();
        } finally {
            lock.readLock().unlock();
        }
    }




}
