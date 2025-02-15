import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth-service.service';

//TODO Check why Interceptor don´t Intercept

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (this.authService.isLoggedIn()) {
      console.log(
        'Este es el token en el header: ' + this.authService.getToken()
      );
      const clonedRequest = req.clone({
        headers: req.headers.set('auth', `${this.authService.getToken()}`),
      });
      return next.handle(clonedRequest);
    }
    return next.handle(req);
  }
}
