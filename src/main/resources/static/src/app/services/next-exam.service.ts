import { Injectable } from '@angular/core';
import { PreguntaApiQuestionsService } from '../services/pregunta-api-questions.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AnswersService } from './answer.service';
import { GetExamUserService } from './get-exam-user.service';
import { Exam } from '../Models/Exam';

@Injectable({
  providedIn: 'root',
})
export class NextExamService {
  private userExam!: boolean;
  private userExams!: Exam[];
  private selectedIndex!: number;
  testForm: FormGroup;

  constructor(
    private readonly fb: FormBuilder,
    private readonly router: Router,
    private readonly preguntaApiQuestionsService: PreguntaApiQuestionsService,
    private readonly answersService: AnswersService,
    private readonly getExamUserService: GetExamUserService
  ) {
    this.initializeVariables();

    this.testForm = this.fb.group({
      category: [null],
      difficulty: [null],
      testCount: [null],
    });
  }

  private initializeVariables() {
    this.userExam = this.answersService.getIsUserExam();
    this.userExams = this.answersService.getUserExams();
    this.selectedIndex = this.answersService.getSelectedIndex();
  }

  private createTestRequest(): string {
    const { category, difficulty, testCount } = this.testForm.value;
    const url = `https://www.preguntapi.dev/api/categories/${category}?level=${difficulty}&limit=${testCount}`;
    console.log('Url desde generar url: ' + url);
    return url;
  }

  setTestForm(testForm: FormGroup) {
    this.testForm = testForm;
  }

  private nextExamApi() {
    const apiUrl = this.createTestRequest();
    this.preguntaApiQuestionsService.getQuestions(apiUrl).subscribe({
      next: (questions) => {
        this.router.navigate(['test'], {
          state: { questions },
        });
      },
      error: (error) => {
        console.error('Error fetching questions', error);
      },
    });
  }

  private nextUserExam(): void {
    const nextExam = this.getNextUserExam();
    this.router.navigate(['test'], {
      state: {
        //TODO: revisar el questions
        questions:
          this.getExamUserService.transformExamtoQuestionsArray(nextExam),
        isUserExam: true,
        selectedIndex: this.selectedIndex,
        exams: this.userExams,
      },
    });
  }

  private getNextUserExam(): Exam {
    this.checkSelectedIndex();
    const exam = this.userExams[this.selectedIndex];
    this.selectedIndex++;
    this.answersService.setSelectedIndex(this.selectedIndex);
    return exam;
  }

  private checkSelectedIndex() {
    if (this.selectedIndex >= this.userExams.length) {
      this.selectedIndex = 0;
      this.answersService.setSelectedIndex(0);
    }
  }

  nextExam() {
    this.initializeVariables();
    if (this.userExam) {
      const nextExam = this.getNextUserExam();
      this.nextUserExam();
    } else {
      this.nextExamApi();
    }
  }
}
