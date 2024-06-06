import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AnswersService {
  private score: number = 0;
  private questions: number = 0;

  setScore(score: number): void {
    this.score = score;
  }
  setQuestions(questions: number): void {
    this.questions = questions;
  }

  getScore(): number {
    return this.score;
  }
  getQuestions(): number {
    return this.questions;
  }
}
