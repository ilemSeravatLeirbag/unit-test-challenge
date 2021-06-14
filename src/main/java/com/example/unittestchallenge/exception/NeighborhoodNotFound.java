package com.example.unittestchallenge.exception;

public class NeighborhoodNotFound extends RuntimeException {

    public NeighborhoodNotFound(String n) {
        super(String.format("A vizinhança %s não está cadastrada.",n));
    }

}
