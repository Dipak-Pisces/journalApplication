package com.dipaklearn.journalApplication.APIResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherResponse {

    protected Current current;

    @Getter
    @Setter
    public class Current {

        @JsonProperty("temperature")
        public Integer temperature;

        @JsonProperty("weather_descriptions")
        public List<String> weatherDescriptions;

        @JsonProperty("feelslike")
        public Integer feelslike;
    }
}

