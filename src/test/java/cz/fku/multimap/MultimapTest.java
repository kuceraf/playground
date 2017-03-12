package cz.fku.multimap;

import com.google.common.collect.Multimap;
import cz.fku.tree.TreeTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 12.03.2017.
 */
public class MultimapTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(MultimapTest.class);

    private final RecordService recordService = RecordService.getInstance();

    @Test
    public void testMultimap(){
        List<Record> records = new ArrayList<>();
        records.addAll(recordService.generateRecords(5, 1L));
        records.addAll(recordService.generateRecords(5, 2L));

        Multimap recordMultimap = records.stream()
                .collect(MultimapCollector.toMultimap(Record::getGroup));

        Assert.assertNotNull(recordMultimap);
    }
}

