package com.example.barappapi.common;

import javax.swing.text.html.parser.Entity;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }

}
