import { Injectable } from '@angular/core';
import { PreguntaApiQuestionsService } from '../services/pregunta-api-questions.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
@Injectable({
  providedIn: 'root',
})
export class NextExamService {
  testForm: FormGroup;
  constructor(
    private readonly fb: FormBuilder,
    private readonly router: Router,
    private readonly preguntaApiQuestionsService: PreguntaApiQuestionsService
  ) {
    this.testForm = this.fb.group({
      category: [null],
      difficulty: [null],
      testCount: [null],
    });
  }

  private createTestRequest(): string {
    let url = '';
    const { category, difficulty, testCount } = this.testForm.value;
    url = `https://www.preguntapi.dev/api/categories/${category}?level=${difficulty}&limit=${testCount}`;
    console.log('Url desde generar url: ' + url);
    return url;
  }
  setTestForm(testForm: FormGroup) {
    this.testForm = testForm;
  }
  routeNextExam(questions: any) {
    this.router.navigate(['test'], {
      state: { questions: questions },
    });
  }
  nextExam() {
    const apiUrl = this.createTestRequest();
    this.preguntaApiQuestionsService.getQuestions(apiUrl).subscribe({
      next: (questions) => {
        this.router.navigate(['test'], {
          state: { questions: questions },
        });
      },
      error: (error) => {
        console.error('Error fetching questions', error);
      },
    });
  }
}
