package cz.fku.multimap;

/**
 * Created by Filip on 12.03.2017.
 */
public class Record {
    private Long id;
    private Long group;
    private String text;



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

    //BUILDER
    public static final class RecordBuilder {
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

        public Record build() {
            Record record = new Record();
            record.setId(id);
            record.setGroup(group);
            record.setText(text);
            return record;
        }
    }
}
