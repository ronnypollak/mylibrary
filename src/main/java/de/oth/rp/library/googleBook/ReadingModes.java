
package de.oth.rp.library.googleBook;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "image"
})
@Generated("jsonschema2pojo")
public class ReadingModes {

    @JsonProperty("text")
    private Boolean text;
    @JsonProperty("image")
    private Boolean image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text")
    public Boolean getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(Boolean text) {
        this.text = text;
    }

    @JsonProperty("image")
    public Boolean getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Boolean image) {
        this.image = image;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
