package com.example.demo.teams;

public class TeamNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    TeamNotFoundException(Long id) {
        super("Team " + id + " not found");
    }
}