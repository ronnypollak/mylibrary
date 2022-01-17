
package de.oth.rp.library.openlib;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "numFound",
    "start",
    "numFoundExact",
    "docs"
})
@Generated("jsonschema2pojo")
public class OpenLibrary {

    @JsonProperty("numFound")
    private Integer numFound;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("numFoundExact")
    private Boolean numFoundExact;
    @JsonProperty("docs")
    private List<OLAuthor> OLAuthors = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("numFound")
    public Integer getNumFound() {
        return numFound;
    }

    @JsonProperty("numFound")
    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    @JsonProperty("start")
    public Integer getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(Integer start) {
        this.start = start;
    }

    @JsonProperty("numFoundExact")
    public Boolean getNumFoundExact() {
        return numFoundExact;
    }

    @JsonProperty("numFoundExact")
    public void setNumFoundExact(Boolean numFoundExact) {
        this.numFoundExact = numFoundExact;
    }

    @JsonProperty("docs")
    public List<OLAuthor> getOLAuthors() {
        return OLAuthors;
    }

    @JsonProperty("docs")
    public void setOLAuthors(List<OLAuthor> OLAuthors) {
        this.OLAuthors = OLAuthors;
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
        sb.append(OpenLibrary.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("numFound");
        sb.append('=');
        sb.append(((this.numFound == null)?"<null>":this.numFound));
        sb.append(',');
        sb.append("start");
        sb.append('=');
        sb.append(((this.start == null)?"<null>":this.start));
        sb.append(',');
        sb.append("numFoundExact");
        sb.append('=');
        sb.append(((this.numFoundExact == null)?"<null>":this.numFoundExact));
        sb.append(',');
        sb.append("docs");
        sb.append('=');
        sb.append(((this.OLAuthors == null)?"<null>":this.OLAuthors));
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
