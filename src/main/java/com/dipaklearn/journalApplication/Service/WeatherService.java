package com.dipaklearn.journalApplication.Service;

import com.dipaklearn.journalApplication.APIResponse.WeatherResponse;
import com.dipaklearn.journalApplication.Cache.AppCache;
import com.dipaklearn.journalApplication.Constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get(AppCache.keys.WEATHER_API_STRING)
                .replace(Placeholders.TOKEN, appCache.APP_CACHE.get(AppCache.keys.WEATHER_API_TOKEN))
                .replace(Placeholders.CITY, city.trim());

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }

}
