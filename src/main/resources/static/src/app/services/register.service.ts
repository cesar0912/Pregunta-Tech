import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface User {
  email: string;
  password: string;
  name: string;
  surname: string;
}

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private apiUrl = 'http://localhost:8080/register';

  constructor(private http: HttpClient) {}

  sendRegister(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }
}
