package com.example.HusenAnsari.myapplication.backend;

import com.example.JokeBox;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {
    public String getJoke() {
        return JokeBox.getRandomJoke();
    }


}