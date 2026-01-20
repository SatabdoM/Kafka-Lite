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

}
