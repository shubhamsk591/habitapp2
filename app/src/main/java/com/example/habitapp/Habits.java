package com.example.habitapp;

public class Habits {
    int id;
    String Name,Question,Target,Unit,Notes,Date;
    int reminder,completed;
    static boolean modify=false;
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

    public void setDate(String date){
        this.Date=date;
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
    public String getDate(){
        return Date;
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
    public static void setModify(boolean modify){Habits.modify = modify;}

    public static boolean getModify(){ return Habits.modify;}


}
