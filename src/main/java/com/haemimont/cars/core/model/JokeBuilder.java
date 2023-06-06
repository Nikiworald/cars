package com.haemimont.cars.core.model;


public class JokeBuilder {
    private String type;
    private String setUp;
    private String punchLine;
    private int id;

    public static JokeBuilder newInstance() {
        return new JokeBuilder();
    }

    public String getType() {
        return type;
    }

    public JokeBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public String getSetUp() {
        return setUp;
    }

    public JokeBuilder setSetUp(String setUp) {
        this.setUp = setUp;
        return this;
    }

    public String getPunchLine() {
        return punchLine;
    }

    public int getId() {
        return id;
    }

    public JokeBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public JokeBuilder setPunchline(String punchLine) {
        this.punchLine = punchLine;
        return this;
    }

    public Joke build() {
        return new Joke(this);
    }
}
