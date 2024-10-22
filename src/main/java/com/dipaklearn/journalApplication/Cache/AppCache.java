package com.dipaklearn.journalApplication.Cache;

import com.dipaklearn.journalApplication.Entity.ConfigurationData;
import com.dipaklearn.journalApplication.Repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API_STRING,
        WEATHER_API_TOKEN;
    }

    @Autowired
    private ConfigurationRepository configurationRepository;

    public HashMap<String,String> APP_CACHE = null;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigurationData> list = configurationRepository.findAll();
        for(ConfigurationData entry : list){
            APP_CACHE.put(entry.getKey(), entry.getValue());
        }
    }


}
