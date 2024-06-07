import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { GetExamUserService } from 'src/app/services/get-exam-user.service';
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

  constructor(private readonly getExamUserService: GetExamUserService) {}

  ngOnInit(): void {}

  getExams(): void {
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
    console.log('Selected Exam:', exam);
  }
}
