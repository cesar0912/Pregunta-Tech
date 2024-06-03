import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../Models/User';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) {}

  sendRegister(user: User): Observable<any> {
    return this.http.post(this.apiUrl, user, { responseType: 'text' }).pipe(
      map((response) => {
        try {
          return JSON.parse(response);
        } catch (e) {
          return response;
        }
      })
    );
  }
}
