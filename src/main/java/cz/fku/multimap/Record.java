package cz.fku.multimap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 12.03.2017.
 */
public class Record {
    //id is unique only within the group
    private Long id;
    private Long group;
    private String text;

    private List<Record> relatedRecords;


    //GETTERS SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Record> getRelatedRecords() {
        if(relatedRecords == null){
            relatedRecords = new ArrayList<>();
        }
        return relatedRecords;
    }

    public void setRelatedRecords(List<Record> relatedRecords) {
        this.relatedRecords = relatedRecords;
    }

    public void addRelatedRecord(Record relatedRelated) {
        if(relatedRecords == null){
            relatedRecords = new ArrayList<>();
        }
        relatedRelated.addRelatedRecord(relatedRelated);
    }

    //CLONE
    public Record clone(){
        return Record.RecordBuilder.aRecord()
                .withId(this.id)
                .withGroup(this.group)
                .withText(this.text)
                .build();
    }

    //BUILDER
    public static final class RecordBuilder {
        List<Record> relatedRecords;
        private Long id;
        private Long group;
        private String text;

        private RecordBuilder() {
        }

        public static RecordBuilder aRecord() {
            return new RecordBuilder();
        }

        public RecordBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RecordBuilder withGroup(Long group) {
            this.group = group;
            return this;
        }

        public RecordBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public RecordBuilder withRelatedRecords(List<Record> relatedRecords) {
            this.relatedRecords = relatedRecords;
            return this;
        }

        public Record build() {
            Record record = new Record();
            record.setId(id);
            record.setGroup(group);
            record.setText(text);
            record.setRelatedRecords(relatedRecords);
            return record;
        }
    }
}
