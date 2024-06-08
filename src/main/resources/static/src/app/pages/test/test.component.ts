import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  FormArray,
} from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { HeaderComponent } from '../header/header.component';
import { Router } from '@angular/router';
import { Question } from 'src/app/Models/Question';
import { AnswersService } from '../../services/answer.service';
import { FinalExamViewComponent } from '../final-exam-view/final-exam-view.component';
import { Exam } from 'src/app/Models/Exam';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    HeaderComponent,
    FinalExamViewComponent,
  ],
})
export class TestComponent implements OnInit {
  questions: Question[] = [];
  currentQuestionIndex: number = 0;
  score: number = 0;
  testForm!: FormGroup;
  answersSelected: boolean = false;
  examFinished: boolean = false;
  isUserExamTestComponent: boolean = false;
  exams: Exam[] = [];
  selectedIndex: number = 0;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private answersService: AnswersService
  ) {
    const navigation = this.router.getCurrentNavigation();
    this.questions = navigation?.extras?.state?.['questions'] || [];
    this.isUserExamTestComponent =
      navigation?.extras?.state?.['isUserExam'] || false;
    this.exams = navigation?.extras?.state?.['exams'] || [];
    this.selectedIndex = navigation?.extras?.state?.['selectedIndex'] || 0;
    this.answersService.setQuestions(this.questions);
    this.answersService.setScore(0);
    this.answersService.setUserExams(this.exams);
    this.answersService.setSelectedIndex(this.selectedIndex);
    this.answersService.setIsUserExam(this.isUserExamTestComponent);
    this.testForm = this.fb.group({
      answers: this.fb.array([]),
    });
  }

  ngOnInit() {
    if (this.questions.length > 0) {
      this.loadQuestion();
    }
  }

  get answers(): FormArray {
    return this.testForm.get('answers') as FormArray;
  }

  loadQuestion(): void {
    const currentQuestion = this.questions[this.currentQuestionIndex];
    this.answers.clear();
    this.answersService.setAmount(this.questions.length);
    for (const key in currentQuestion.answers) {
      this.answers.push(this.fb.control(false));
    }
  }

  selectAnswer(index: number): void {
    const control = this.answers.at(index);
    control.setValue(!control.value);
  }

  onSubmit(): void {
    const currentQuestion = this.questions[this.currentQuestionIndex];
    const selectedAnswers = this.testForm.value.answers
      .map((checked: boolean, i: number) =>
        checked ? `answer_${String.fromCharCode(97 + i)}` : null
      )
      .filter((v: string | null) => v !== null);
    const correctAnswers = [currentQuestion.correct_answer];

    const allCorrect = selectedAnswers.every((answer: string) =>
      correctAnswers.includes(answer)
    );
    const allSelectedCorrect = correctAnswers.every((answer) =>
      selectedAnswers.includes(answer)
    );

    if (allCorrect && allSelectedCorrect) {
      this.score++;
      this.answersService.setScore(this.score);
    }

    this.answersSelected = true;
  }

  nextQuestion(): void {
    this.answersSelected = false;
    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;
      this.loadQuestion();
      this.testForm.reset();
    } else {
      this.examIsFinished();
    }
  }

  private examIsFinished(): void {
    this.router.navigate(['final-view-exam']);
  }
  public getExamFinished(): boolean {
    return this.answersService.getExamFinished();
  }
  getScore(): number {
    return this.score;
  }
  getQuestionsLength(): number {
    return this.questions.length;
  }
}
