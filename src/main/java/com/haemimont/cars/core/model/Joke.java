package com.haemimont.cars.core.model;

public class Joke {
    private String type;
    private String setUp;
    private String punchLine;
    private int id;
    public Joke(JokeBuilder jokeBuilder){
        this.id = jokeBuilder.getId();
        this.type= jokeBuilder.getType();
        this.punchLine = jokeBuilder.getPunchLine();
        this.setUp = jokeBuilder.getSetUp();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetUp() {
        return setUp;
    }

    public void setSetUp(String setUp) {
        this.setUp = setUp;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public void setPunchLine(String punchLine) {
        this.punchLine = punchLine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
