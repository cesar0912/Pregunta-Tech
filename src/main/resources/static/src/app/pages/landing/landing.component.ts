import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';

interface Category {
  name: string;
  count_questions: number;
  link: string;
}

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    HeaderComponent,
  ],
})
export class LandingComponent implements OnInit {
  categories: Category[] = [
    {
      name: 'cpp',
      count_questions: 29,
      link: 'http://www.preguntapi.dev/api/categories/cpp',
    },
    {
      name: 'csharp',
      count_questions: 35,
      link: 'http://www.preguntapi.dev/api/categories/csharp',
    },
    {
      name: 'css',
      count_questions: 38,
      link: 'http://www.preguntapi.dev/api/categories/css',
    },
    {
      name: 'html',
      count_questions: 35,
      link: 'http://www.preguntapi.dev/api/categories/html',
    },
    {
      name: 'java',
      count_questions: 35,
      link: 'http://www.preguntapi.dev/api/categories/java',
    },
    {
      name: 'javascript',
      count_questions: 40,
      link: 'http://www.preguntapi.dev/api/categories/javascript',
    },
    {
      name: 'kotlin',
      count_questions: 25,
      link: 'http://www.preguntapi.dev/api/categories/kotlin',
    },
    {
      name: 'php',
      count_questions: 24,
      link: 'http://www.preguntapi.dev/api/categories/php',
    },
    {
      name: 'python',
      count_questions: 21,
      link: 'http://www.preguntapi.dev/api/categories/python',
    },
    {
      name: 'sql',
      count_questions: 33,
      link: 'http://www.preguntapi.dev/api/categories/sql',
    },
    {
      name: 'swift',
      count_questions: 20,
      link: 'http://www.preguntapi.dev/api/categories/swift',
    },
    {
      name: 'typescript',
      count_questions: 20,
      link: 'http://www.preguntapi.dev/api/categories/typescript',
    },
  ];

  difficulties: string[] = ['facil', 'normal', 'dificil'];
  testCounts: number[] = [5, 10, 15, 20];

  constructor(private readonly router: Router) {}

  ngOnInit() {}

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }

  public navigateRegister(): void {
    this.router.navigate(['register']);
  }

  public navigateTest(): void {
    this.router.navigate(['test']);
  }
}
