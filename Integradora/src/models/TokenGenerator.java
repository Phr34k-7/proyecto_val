package models;

import java.util.UUID;

public class TokenGenerator {

    public static String generarToken() {
        return UUID.randomUUID().toString();
    }
}
