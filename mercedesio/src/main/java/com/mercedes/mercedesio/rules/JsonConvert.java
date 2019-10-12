package com.mercedes.mercedesio.rules;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class JsonConvert {

    public void convert(InputStream jsonInputStream) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonInputStream);

        root.isMissingNode();

        JsonNode objectDealers = root.get("dealers");
        for(JsonNode dealersNode : objectDealers){
            System.out.println("id: "+dealersNode.get("id").asText()
                    +" name: "+dealersNode.get("name").asText()
                    +" latitude: "+dealersNode.get("latitude").asText()
                    +" longitude: "+dealersNode.get("longitude").asText());
        }
    }




}
