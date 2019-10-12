package com.mercedes.mercedesio.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ApplicationUtilities {

    public static LocalDateTime convertStringToLocalDate(String strLocalDateTime){
        return LocalDateTime.parse(strLocalDateTime);
    }

}
