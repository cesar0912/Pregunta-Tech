package es.metrica.PreguntaTech.model;

public class Category {
    private String name;
    private int count_questions;
    private String link;


    public Category() {}

    public Category(String name, int count_questions, String link) {
        this.name = name;
        this.count_questions = count_questions;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getcount_questions() {
        return count_questions;
    }

    public void setcount_questions(int countQuestions) {
        this.count_questions = countQuestions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
