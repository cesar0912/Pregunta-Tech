package es.metrica.Pregunta_Tech.model;

import java.util.List;

public class CategoryResponse {
	
    private List<Category> categories;
    private int totalCategories;
    private int totalQuestions;


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(int totalCategories) {
        this.totalCategories = totalCategories;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

	protected CategoryResponse(List<Category> categories, int totalCategories, int totalQuestions) {
		super();
		this.categories = categories;
		this.totalCategories = totalCategories;
		this.totalQuestions = totalQuestions;
	}

	protected CategoryResponse() {
		super();
	}
}