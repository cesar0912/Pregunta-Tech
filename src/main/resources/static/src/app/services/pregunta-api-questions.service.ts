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
  urlEnvio: string = 'http://localhost:8080/test';

  constructor(private http: HttpClient) {}

  getQuestions(urlTest: string): Observable<Question[]> {
    return this.http.post<Question[]>(this.urlEnvio, { url: urlTest });
  }
}
