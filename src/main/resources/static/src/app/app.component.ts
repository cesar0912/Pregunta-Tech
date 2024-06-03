import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { PreguntaApiCategoriesService } from './services/pregunta-api-categories.service';
import { PreguntaApiQuestionsService } from './services/pregunta-api-questions.service';
import { RegisterService } from './services/register.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule],
  providers: [
    PreguntaApiCategoriesService,
    PreguntaApiQuestionsService,
    RegisterService,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'PreguntaTech';
}
