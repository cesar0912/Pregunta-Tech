import { Injectable } from '@angular/core';
import { CookieService } from './cookie.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly TOKEN_KEY = 'auth_token';

  constructor(private readonly cookieService: CookieService) {}

  setToken(token: string): void {
    this.cookieService.setCookie(this.TOKEN_KEY, token, 7);
  }

  getToken(): string | null {
    return this.cookieService.getCookie(this.TOKEN_KEY);
  }

  clearToken(): void {
    this.cookieService.deleteCookie(this.TOKEN_KEY);
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }
}
