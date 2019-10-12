package com.mercedes.mercedesio.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.mercedesio.model.entities.Dealer;
import com.mercedes.mercedesio.rules.JsonConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;


@Service
public class JsonLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonLoadService.class);

    private List<String> strArray = new ArrayList<String>();

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JsonConvert jsonConvert;

    public JsonLoadService(){

    }

    @PostConstruct
    public void init(){
        Resource resource = resourceLoader.getResource("classpath:"+"json/dataset.json");
        try
        {
            jsonConvert.convert(resource.getInputStream());
        }catch (Exception e){
            LOGGER.error("Resource not found: ", e);
            e.printStackTrace();
        }
    }

}
