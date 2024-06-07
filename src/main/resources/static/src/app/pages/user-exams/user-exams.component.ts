import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { GetExamUserService } from 'src/app/services/get-exam-user.service';
import { Exam } from 'src/app/Models/Exam';

@Component({
  selector: 'app-user-exams',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './user-exams.component.html',
  styleUrl: './user-exams.component.css',
})
export class UserExamsComponent {
  userExams: Exam[] = [];
  constructor(private readonly getExamUserService: GetExamUserService) {}

  getExams(): void {
    this.getExamUserService.getExamsUser().subscribe({
      next: (response) => {
        this.userExams = response.exams;
        console.log(this.userExams);
      },
      error: (error) => {
        console.error('Error fetching questions', error);
      },
    });
  }
}
