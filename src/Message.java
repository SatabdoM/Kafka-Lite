import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String key;
    private final String value;
    private final long timestamp;
    private final Map<String, String> headers; //additional metadata 8
    private long offset = -1;

    public Message(String key, String value, long timestamp, Map<String, String> headers) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.headers = new HashMap<>(headers) {
        };
    }

    public Message(String key, String value) {
        this(key, value, System.currentTimeMillis(), new HashMap<>());
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getOffset() {
        return offset;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("Message{key='%s', value='%s', timestamp=%d, offset=%d}",
                key, value, timestamp, offset);
    }
    public int getPartitionHash() {
        return key != null ? key.hashCode() : value.hashCode();
    }
}
