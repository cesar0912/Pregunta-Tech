import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Exam } from '../Models/Exam';
import { AuthService } from '../services/auth-service.service';

@Injectable({
  providedIn: 'root',
})
export class ExamService {
  private apiUrl = 'http://localhost:8080/exam';

  constructor(private http: HttpClient, private authService: AuthService) {}

  saveExam(exam: Exam): Observable<Exam> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      auth: token ? token : '',
    });

    return this.http.post<Exam>(this.apiUrl, exam, { headers });
  }
}
