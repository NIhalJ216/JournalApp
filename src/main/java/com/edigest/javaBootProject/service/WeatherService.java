package com.edigest.javaBootProject.service;

import com.edigest.javaBootProject.api.response.WeatherReponse;
import com.edigest.javaBootProject.cache.AppCache;
import com.edigest.javaBootProject.constants.Placeholders;
import com.edigest.javaBootProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String apikey;

//    private static final String API = "http://api.weatherstack.com/current?access_key=APIKEY&query=CITY";
    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherReponse getWeather(String city){
        WeatherReponse weatherReponse = redisService.get("Weather_of_" + city, WeatherReponse.class);
        if(weatherReponse != null){
            return weatherReponse;
        }else{
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apikey);
            ResponseEntity<WeatherReponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherReponse.class);
            WeatherReponse body = response.getBody();
            if(body != null){
                redisService.set("Weather_of_" + city, body, 300l);
            }
            return body;
        }
    }

    // For POST api calls to thirdparty api
//    public void postWeather(String city){
//        String finalAPI = API.replace("CITY", city).replace("APIKEY", apikey);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "value");
//        User user = User.builder().userName("johndoe").password("john").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
//
//        ResponseEntity<WeatherReponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherReponse.class);
//    }
}
