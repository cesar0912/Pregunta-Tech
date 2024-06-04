package es.metrica.Pregunta_Tech.model;

public class Category {
	
    private String name;
    private int countQuestions;
    private String link;


    public Category() {}

    public Category(String name, int countQuestions, String link) {
        this.name = name;
        this.countQuestions = countQuestions;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
