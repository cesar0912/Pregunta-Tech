import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-final-exam-view',
  templateUrl: './final-exam-view.component.html',
  styleUrls: ['./final-exam-view.component.css'],
  standalone: true,
  imports: [],
})
export class FinalExamViewComponent implements OnInit {
  constructor(private readonly router: Router) {}

  ngOnInit() {
    console.log("compnente creadp");
  }
  navigateLanding() {
    this.router.navigate(['landing']);
  }
}
