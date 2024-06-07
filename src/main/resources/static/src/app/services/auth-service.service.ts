import { Injectable } from '@angular/core';

//TODO Implement LocalStorage, doesn´t work
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private token: string | null = null;

  constructor() {}

  setToken(token: string): void {
    this.token = token;
  }

  getToken(): string | null {
    return this.token;
  }

  clearToken(): void {
    this.token = null;
  }

  isLoggedIn(): boolean {
    return this.token !== null;
  }
}
