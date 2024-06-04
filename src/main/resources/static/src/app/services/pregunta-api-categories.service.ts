import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface ApiResponseCategory {
  categories: Category[];
  totalCategories: number;
  totalQuestions: number;
}

export interface Category {
  name: string;
  count_questions: number;
  link: string;
}

@Injectable({
  providedIn: 'root',
})
export class PreguntaApiCategoriesService {
  private apiUrl = 'http://localhost:8080/categories';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<ApiResponseCategory> {
    return this.http.get<ApiResponseCategory>(this.apiUrl);
  }
}
