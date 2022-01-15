
package de.oth.rp.library.openlib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "type",
    "name",
    "alternate_names",
    "birth_date",
    "top_work",
    "work_count",
    "top_subjects",
    "_version_"
})
@Generated("jsonschema2pojo")
public class OLAuthor {

    @JsonProperty("key")
    private String key;
    @JsonProperty("type")
    private String type;
    @JsonProperty("name")
    private String name;
    @JsonProperty("alternate_names")
    private List<String> alternateNames = null;
    @JsonProperty("birth_date")
    private String birthDate;
    @JsonProperty("top_work")
    private String topWork;
    @JsonProperty("work_count")
    private Integer workCount;
    @JsonProperty("top_subjects")
    private List<String> topSubjects = null;
    @JsonProperty("_version_")
    private Long version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("alternate_names")
    public List<String> getAlternateNames() {
        return alternateNames;
    }

    @JsonProperty("alternate_names")
    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    @JsonProperty("birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birth_date")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("top_work")
    public String getTopWork() {
        return topWork;
    }

    @JsonProperty("top_work")
    public void setTopWork(String topWork) {
        this.topWork = topWork;
    }

    @JsonProperty("work_count")
    public Integer getWorkCount() {
        return workCount;
    }

    @JsonProperty("work_count")
    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    @JsonProperty("top_subjects")
    public List<String> getTopSubjects() {
        return topSubjects;
    }

    @JsonProperty("top_subjects")
    public void setTopSubjects(List<String> topSubjects) {
        this.topSubjects = topSubjects;
    }

    @JsonProperty("_version_")
    public Long getVersion() {
        return version;
    }

    @JsonProperty("_version_")
    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OLAuthor.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("alternateNames");
        sb.append('=');
        sb.append(((this.alternateNames == null)?"<null>":this.alternateNames));
        sb.append(',');
        sb.append("birthDate");
        sb.append('=');
        sb.append(((this.birthDate == null)?"<null>":this.birthDate));
        sb.append(',');
        sb.append("topWork");
        sb.append('=');
        sb.append(((this.topWork == null)?"<null>":this.topWork));
        sb.append(',');
        sb.append("workCount");
        sb.append('=');
        sb.append(((this.workCount == null)?"<null>":this.workCount));
        sb.append(',');
        sb.append("topSubjects");
        sb.append('=');
        sb.append(((this.topSubjects == null)?"<null>":this.topSubjects));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
