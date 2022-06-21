package utilities.PoJos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Details {
    @JsonProperty("USER_NAME")
    private String USER_NAME;
    @JsonProperty("PASSWORD")
    private String PASSWORD;


}
