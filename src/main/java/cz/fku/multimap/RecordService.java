package cz.fku.multimap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Filip on 12.03.2017.
 */
public class RecordService {
    private static RecordService instance = null;

    private RecordService() {
        // Exists only to defeat instantiation.
    }

    public static RecordService getInstance() {
        if(instance == null) {
            instance = new RecordService();
        }
        return instance;
    }

    public List<Record> generateRecords(int number, Long groupId) {
        AtomicLong seq = new AtomicLong();
        List<Record> records = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            records.add(
                    Record.RecordBuilder.aRecord()
                            .withId(seq.incrementAndGet())
                            .withGroup(groupId)
                            .withText("In group " + groupId)
                            .build()
            );
        }
        return records;
    }
}
