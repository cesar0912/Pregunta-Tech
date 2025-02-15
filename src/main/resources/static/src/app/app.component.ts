import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PreguntaApiCategoriesService } from './services/pregunta-api-categories.service';
import { PreguntaApiQuestionsService } from './services/pregunta-api-questions.service';
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { AnswersService } from './services/answer.service';
import { AuthService } from './services/auth-service.service';
import { AuthInterceptor } from './Interceptors/AuthInterceptor';
import { ExamService } from './services/exam.service';
import { NextExamService } from './services/next-exam.service';
import { GetExamUserService } from './services/get-exam-user.service';
import { CookieService } from './services/cookie.service';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule],
  providers: [
    PreguntaApiCategoriesService,
    PreguntaApiQuestionsService,
    RegisterService,
    LoginService,
    AnswersService,
    AuthService,
    ExamService,
    NextExamService,
    GetExamUserService,
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'PreguntaTech';
}
