package cz.fku.multimap;

import com.google.common.collect.Interner;
import com.google.common.collect.Multimap;
import cz.fku.tree.TreeTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Created by Filip on 12.03.2017.
 */
public class MultimapTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(MultimapTest.class);

    private final RecordService recordService = RecordService.getInstance();
    private static final Long GROUP_ID_1 = 1L;
    private static final Long GROUP_ID_2 = 2L;

    @Test
    public void testMultimap(){
        List<Record> records = new ArrayList<>();
        records.addAll(recordService.generateRecords(5, GROUP_ID_1));
        records.addAll(recordService.generateRecords(5, GROUP_ID_2));

        Multimap recordMultimap = records.stream()
                .collect(MultimapCollector.toMultimap(Record::getGroup));

        Assert.assertNotNull(recordMultimap);
    }

    @Test
    public void testComposedKey() {
        List<Record> records = new ArrayList<>();
        List<Record> relatedRecords = new ArrayList<>();

        //CREATE RECORDS
        records.addAll(recordService.generateRecords(10, GROUP_ID_1));
        records.addAll(recordService.generateRecords(5, GROUP_ID_2));

        //CREATE RELATED RECORDS
        //deep copy of record list
        for (Record record : records) {
            relatedRecords.add(record.clone());
        }
        for (Record record : records) {
            relatedRecords.add(record.clone());
        }

        //CREATE RECORDS MULTIMAP WITH COMPOSED KEY
        //LongKey = record ID, group ID
        Multimap<LongKey, Record> relatedRecordMultimap = relatedRecords.stream()
                .collect(MultimapCollector.toMultimap(
                        record -> {
                            return new LongKey(record.getId(),record.getGroup());
                        }
                ));

        //ASSIGN RELATED RECORDS TO RECORD (not necessary creating recordMultimap)
        Multimap<LongKey, Record> recordMultimap = records.stream()
                .collect(MultimapCollector.toMultimap(
                        //key function
                        record -> new LongKey(record.getId(), record.getGroup()),
                        //value function - pair relatedRecord with record by composed key (id, group)
                        record -> mapRelatedRecord.apply(record, relatedRecordMultimap))
                );

        //TESTS
        Assert.assertNotNull(recordMultimap);

        List<LongKey> keysG1 = recordMultimap.keys().stream()
                .filter(key -> key.getGroupId() == GROUP_ID_1)
                .collect(Collectors.toList());
        Assert.assertEquals("In group 1 must be 10 records",keysG1.size(), 10);

        for (LongKey key : keysG1) {
            Collection<Record> recordsG1 = recordMultimap.get(key);
            recordsG1.forEach(record -> {
                Assert.assertEquals("all record in group 1 must have two related records",
                        record.getRelatedRecords().size(), 2);
            });
        }

        List<LongKey> keysG2 = recordMultimap.keys().stream()
                .filter(key -> key.getGroupId() == GROUP_ID_2)
                .collect(Collectors.toList());
        Assert.assertEquals("In group 2 must be 5 records",keysG2.size(), 5);

        for (LongKey key : keysG2) {
            Collection<Record> recordsG1 = recordMultimap.get(key);
            recordsG1.forEach(record -> {
                Assert.assertEquals("all record in group 2 must have two related records",
                        record.getRelatedRecords().size(), 2);
            });
        }
    }

    //maps related record to record it belongs (affiliation is defined by compound key)
    private BiFunction<Record, Multimap<LongKey, Record>, Record> mapRelatedRecord = (record, relatedRecordMultimap) -> {
        Collection<Record> records = relatedRecordMultimap.get(new LongKey(record.getId(), record.getGroup()));
        record.getRelatedRecords().addAll(records);
        return record;
    };
}

