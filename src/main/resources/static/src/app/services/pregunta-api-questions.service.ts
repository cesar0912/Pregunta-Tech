import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Question {
  id: string;
  category: string;
  level: string;
  question: string;
  answers: {
    [key: string]: string;
  };
  correct_answer: string;
  feedback?: string;
}

interface QuestionApiResponse {
  questions: Question[];
  totalQuestions: number;
}

@Injectable({
  providedIn: 'root',
})
export class PreguntaApiQuestionsService {
  constructor(private http: HttpClient) {}

  getQuestions(url: string): Observable<QuestionApiResponse> {
    return this.http.get<QuestionApiResponse>(url);
  }
}
