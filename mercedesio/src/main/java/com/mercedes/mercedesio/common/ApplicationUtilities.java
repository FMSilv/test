package com.mercedes.mercedesio.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApplicationUtilities {

    public LocalDateTime convertStringToLocalDate(String strLocalDateTime){
        return LocalDateTime.parse(strLocalDateTime);
    }

    public Double calculateDistanceBetweenToGeograficPoints(Double latitude1, Double longitude1, Double latitude2, Double longitude2){
        double earthRadius = Constantes.EARTH_RADIUS_METERS;

        double latitudeVariation = Math.toRadians(latitude2-latitude1);
        double longitudeVariation = Math.toRadians(longitude2-longitude1);

        /* Haversine formula - determines the great-circle distance between two points on a sphere given their longitudes and latitudes. */
        double a = Math.sin(latitudeVariation/2) * Math.sin(latitudeVariation/2)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.sin(longitudeVariation/2) * Math.sin(longitudeVariation/2);
        double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return earthRadius * c;
    }

}
