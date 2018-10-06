
package net.codetojoy.v2;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class Field {
    private String key;
    private String value;
    private String value_fr;
    private List<Field> subFields;

    // ----------- getter / setter

    public List<Field> getSubFields() { return subFields; }
    public void setSubFields(List<Field> subFields) { this.subFields = subFields; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @JsonProperty("value_fr")
    public String getValueFr() { return value_fr; }
    public void setValueFr(String value_fr) { this.value_fr = value_fr; }
}
