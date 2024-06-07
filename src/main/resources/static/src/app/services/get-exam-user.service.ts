import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth-service.service';
import { Exam } from '../Models/Exam';

@Injectable({
  providedIn: 'root',
})
export class GetExamUserService {
  private apiUrl = 'http://localhost:8080/getExamsByUser';

  constructor(
    private readonly authService: AuthService,
    private http: HttpClient
  ) {}

  getExamsUser(): Observable<Exam[]> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      auth: token ? token : '',
    });
    return this.http.get<Exam[]>(this.apiUrl, { headers });
  }
}
