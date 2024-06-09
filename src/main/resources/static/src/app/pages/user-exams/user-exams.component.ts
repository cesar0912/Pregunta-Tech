import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { GetExamUserService } from 'src/app/services/get-exam-user.service';
import { Router } from '@angular/router';
import { Exam } from 'src/app/Models/Exam';

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

  selectExam(selectedExam: Exam) {
    const selectedIndex = this.userExams.findIndex(
      (exam) => exam === selectedExam
    );
    this.router.navigate(['/test'], {
      state: {
        questions:
          this.getExamUserService.transformExamtoQuestionsArray(selectedExam),
        isUserExam: true,
        selectedIndex: selectedIndex,
        exams: this.userExams,
      },
    });
  }
}
