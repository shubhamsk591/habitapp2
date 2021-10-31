package com.example.habitapp;

public class Habits {
    int id;
    String Name,Question,Target,Unit,Notes;
    int reminder,completed;
    public Habits(){

    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getQuestion() {
        return Question;
    }

    public String getTarget() {
        return Target;
    }

    public String getUnit() {
        return Unit;
    }

    public String getNotes() {
        return Notes;
    }

    public int getReminder() {
        return reminder;
    }

    public int getCompleted() {
        return completed;
    }

    public Habits(int id, String name, String question,  String unit,String target,int reminder,String notes, int completed) {
        this.id = id;
        Name = name;
        Question = question;

        Unit = unit;
        Target = target;
        this.reminder = reminder;
        Notes = notes;

        this.completed = completed;
    }



}
