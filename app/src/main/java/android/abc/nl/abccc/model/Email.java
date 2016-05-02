package android.abc.nl.abccc.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Email {
    private String type;
    private String value;
    private boolean isPrimary;

    public Email() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Email(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

}
