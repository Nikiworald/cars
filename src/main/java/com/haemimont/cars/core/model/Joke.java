package com.haemimont.cars.core.model;

@SuppressWarnings("unused")//they are used in jsp
public class Joke {
    private String punchLine;
    private int id;

    public Joke(JokeBuilder jokeBuilder) {
        this.id = jokeBuilder.getId();
        String TYPE = jokeBuilder.getType();
        this.punchLine = jokeBuilder.getPunchLine();
        String SETUP = jokeBuilder.getSetUp();
    }

// --Commented out by Inspection START (6/22/2023 2:58 PM):
// --Commented out by Inspection START (6/22/2023 2:58 PM):
////    public String getType() {
////        return type;
////    }
//// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public void setType(String type) {
//        this.type = type;
//    }
//
//// --Commented out by Inspection START (6/22/2023 2:58 PM):
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
// --Commented out by Inspection START (6/22/2023 2:58 PM):
////    public String getSetUp() {
////        return setUp;
////    }
//// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

    //   public void setSetUp(String setUp) {
// --Commented out by Inspection START (6/22/2023 2:58 PM):
//        this.setUp = setUp;
//    }
//
//// --Commented out by Inspection START (6/22/2023 2:58 PM):
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
//    public String getPunchLine() {
//        return punchLine;
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

    public void setPunchLine(String punchLine) {
        this.punchLine = punchLine;
    }

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public int getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

    public void setId(int id) {
        this.id = id;
    }
}
