import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth-service.service';
import { Exam } from '../Models/Exam';
import { Question } from '../Models/Question';
import { QuestionExam } from '../Models/QuestionExam';

@Injectable({
  providedIn: 'root',
})
export class GetExamUserService {
  private apiUrl = 'http://localhost:8080/getExamsByUser';

  constructor(
    private readonly authService: AuthService,
    private http: HttpClient
  ) {}

  transformExamtoQuestionsArray(examen: Exam): Question[] {
    return examen.questions.map((pregunta: QuestionExam) => {
      const question: Question = {
        id: pregunta.id.toString(),
        category: pregunta.category,
        level: pregunta.level,
        question: pregunta.question,
        correct_answer: pregunta.correct_answer,
        answers: pregunta.answers.reduce(
          (
            acc: { [key: string]: string },
            respuesta: string,
            index: number
          ) => {
            acc[`answer_${String.fromCharCode(97 + index)}`] = respuesta;
            return acc;
          },
          {}
        ),
      };
      return question;
    });
  }

  getExamsUser(): Observable<Exam[]> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      auth: token ? token : '',
    });
    return this.http.get<Exam[]>(this.apiUrl, { headers });
  }
}
