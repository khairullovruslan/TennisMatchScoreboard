package org.tomato.tennismatchscoreboardweb.util;

import java.util.UUID;

public class RequestParametersValidator {
    private final static RequestParametersValidator INSTANCE = new RequestParametersValidator();

    private RequestParametersValidator() {
    }

    public static RequestParametersValidator getInstance() {
        return INSTANCE;
    }


    public boolean canConvertToInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean canConvertToUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
