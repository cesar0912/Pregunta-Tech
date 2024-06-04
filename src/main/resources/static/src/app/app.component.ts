import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { PreguntaApiCategoriesService } from './services/pregunta-api-categories.service';
import { PreguntaApiQuestionsService } from './services/pregunta-api-questions.service';
import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { AnswersService } from './services/answer.service';

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
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'PreguntaTech';
}
