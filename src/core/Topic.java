package core;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {
    private final String name;
    private final int PartitionCount;
    private final Map<Integer, Partition> partitions;
    private final int replicationFactor;

    public Topic(String name, int partitionCount, int replicationFactor) {
        this.name = name;
        this.PartitionCount = partitionCount;
        this.replicationFactor = replicationFactor;
        this.partitions = new ConcurrentHashMap<>();
        //Initialize partitions for this topic
        for (int i = 0; i < partitionCount; i++) {
            partitions.put(i, new Partition(name, i));
        }
    }

    // get core.Partition by id
    public Partition getPartition(int partitionId) {
        return partitions.get(partitionId);
    }

    // get all partitions
    public Collection<Partition> getPartitions() {
        return partitions.values();
    }

    // Calculate which partition a message should go to based on its key
    public int getPartitionForMessage(Message message) {
        return 0;
    }

    // Append message to the appropriate partition
    public long appendMessage(Message message) {
        //ToDo: implement partitioning logic
        return 0L;
    }

    //Read message from a specific partition and offset
    public Message readMessage(int partitionId, long offset, int maxMessages) {
        //Todo: implement reading logic
        return new Message("", "");
    }

    //Get all leader partitions
    public List<Partition> getLeaderPartitions() {
        //ToDo: implement leader partition retrieval
        return null;
    }

    //Get the total no of messages across all partitions
    public long getTotalMessageCount() {
        //ToDo: implement total message count logic
        return 0L;
    }


}
