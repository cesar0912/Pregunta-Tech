import { Question } from 'src/app/Models/Question';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Exam } from '../Models/Exam';

@Injectable({
  providedIn: 'root',
})
export class AnswersService {
  private questions: Question[] = [];
  private score: number = 0;
  private amount: number = 0;
  private examFinished: boolean = false;
  private isUserExam: boolean = false;
  private userExams: Exam[] = [];
  private selectedIndex: number = 0;

  constructor(private router: Router) {}

  setScore(score: number): void {
    this.score = score;
  }
  setAmount(amount: number): void {
    this.amount = amount;
  }
  setQuestions(questions: Question[]) {
    this.questions = questions;
  }
  setExamFinished(examFinished: boolean) {
    this.examFinished = examFinished;
  }
  getExamFinished(): boolean {
    return this.examFinished;
  }
  getQuestions(): Question[] {
    return this.questions;
  }

  getScore(): number {
    return this.score;
  }
  getAmount(): number {
    return this.amount;
  }
  setIsUserExam(isUserExamComponent: boolean) {
    this.isUserExam = isUserExamComponent;
  }
  getIsUserExam(): boolean {
    return this.isUserExam;
  }

  setUserExams(userExams: Exam[]) {
    this.userExams = userExams;
  }

  getUserExams(): Exam[] {
    return this.userExams;
  }
  setSelectedIndex(selectedIndex: number) {
    this.selectedIndex = selectedIndex;
  }
  getSelectedIndex(): number {
    return this.selectedIndex;
  }

  resetExam(): void {
    this.setExamFinished(false);
    this.router.navigate(['test'], {
      state: { questions: this.questions },
    });
  }
}
