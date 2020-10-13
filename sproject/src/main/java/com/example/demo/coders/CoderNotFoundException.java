package com.example.demo.coders;

public class CoderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    CoderNotFoundException(Long id) {
        super("Coder " + id + " not found");
    }
}