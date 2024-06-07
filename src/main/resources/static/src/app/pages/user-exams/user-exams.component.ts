import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { GetExamUserService } from 'src/app/services/get-exam-user.service';
import { Router } from '@angular/router';
import { Exam } from 'src/app/Models/Exam';
import { Question } from 'src/app/Models/Question';
import { QuestionExam } from 'src/app/Models/QuestionExam';

@Component({
  selector: 'app-user-exams',
  standalone: true,
  imports: [CommonModule, HeaderComponent],
  templateUrl: './user-exams.component.html',
  styleUrls: ['./user-exams.component.css'],
})
export class UserExamsComponent implements OnInit {
  userExams: Exam[] = [];
  paginatedExams: Exam[] = [];
  currentPage = 1;
  examsPerPage = 5;

  constructor(
    private readonly getExamUserService: GetExamUserService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.getExams();
  }

  private getExams(): void {
    this.getExamUserService.getExamsUser().subscribe({
      next: (response) => {
        this.userExams = response;
        this.setPage(1);
      },
      error: (error) => {
        console.error('Error fetching exams', error);
      },
    });
  }

  setPage(page: number): void {
    this.currentPage = page;
    const startIndex = (page - 1) * this.examsPerPage;
    const endIndex = startIndex + this.examsPerPage;
    this.paginatedExams = this.userExams.slice(startIndex, endIndex);
  }

  selectExam(exam: Exam): void {
    this.router.navigate(['/test'], {
      state: { questions: this.transformarExamen(exam) },
    });
  }

  transformarExamen(examen: Exam): Question[] {
    return examen.questions.map((pregunta: QuestionExam) => {
      const question: Question = {
        id: pregunta.id.toString(),
        category: pregunta.category,
        level: pregunta.level,
        question: pregunta.question,
        correct_answer: pregunta.correct_answer,
        answers: pregunta.answers.reduce(
          (
            acc: { [key: string]: string },
            respuesta: string,
            index: number
          ) => {
            acc[`answer_${String.fromCharCode(97 + index)}`] = respuesta;
            return acc;
          },
          {}
        ),
      };
      return question;
    });
  }
}
//mario.rodri9@gmail.com
//12345678aA!
