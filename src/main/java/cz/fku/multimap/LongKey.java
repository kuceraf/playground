package cz.fku.multimap;

/**
 * Created by Filip on 12.03.2017.
 */
public class LongKey {

    private final long id;
    private final long groupId;

    public LongKey(long x, long y) {
        this.id = x;
        this.groupId = y;
    }

    public long getId() {
        return id;
    }

    public long getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongKey)) return false;
        LongKey key = (LongKey) o;
        return id == key.id && groupId == key.groupId;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + groupId;
        return (int) result;
    }

}
