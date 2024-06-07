import { NextExamService } from './../../services/next-exam.service';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import {
  PreguntaApiCategoriesService,
  Category,
} from '../../services/pregunta-api-categories.service';
import { PreguntaApiQuestionsService } from '../../services/pregunta-api-questions.service';
import { CategoryCacheService } from '../../services/category-cache.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
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
  categories: Category[] = [];
  selectedCategory: Category | null = null;
  difficulties: string[] = ['facil', 'normal', 'dificil'];
  testCounts: number[] = [5, 10, 15, 20];

  testForm: FormGroup;

  constructor(
    private readonly fb: FormBuilder,
    private readonly router: Router,
    private readonly preguntaApiCategoriesService: PreguntaApiCategoriesService,
    private readonly preguntaApiQuestionsService: PreguntaApiQuestionsService,
    private readonly categoryCacheService: CategoryCacheService,
    private readonly NextExamService: NextExamService
  ) {
    this.testForm = this.fb.group({
      category: [null, Validators.required],
      difficulty: [null, Validators.required],
      testCount: [null, Validators.required],
    });
  }

  ngOnInit() {
    const cachedCategories = this.categoryCacheService.getCategories();
    if (cachedCategories.length > 0) {
      this.categories = cachedCategories;
    } else {
      this.loadCategories();
    }
  }

  private loadCategories(): void {
    this.preguntaApiCategoriesService.getCategories().subscribe({
      next: (response) => {
        this.categories = response.categories;
        this.categoryCacheService.setCategories(this.categories);
      },
      error: (error) => {
        console.error('Error fetching categories', error);
      },
    });
  }

  public selectCategory(category: Category): void {
    this.selectedCategory = category;
    this.testForm.patchValue({ category: category.name });
  }

  private createTestRequest(): string {
    let url = '';
    if (this.testForm.valid) {
      const { category, difficulty, testCount } = this.testForm.value;
      url = `https://www.preguntapi.dev/api/categories/${category}?level=${difficulty}&limit=${testCount}`;
    }
    this.NextExamService.setTestForm(this.testForm);
    console.log('Url desde generar url: ' + url);
    return url;
  }

  public onSubmit(): void {
    const apiUrl = this.createTestRequest();

    this.preguntaApiQuestionsService.getQuestions(apiUrl).subscribe({
      next: (questions) => {
        this.router.navigate(['test'], {
          state: { questions: questions },
        });
      },
      error: (error) => {
        console.error('Error fetching questions', error);
      },
    });
  }

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }

  public navigateRegister(): void {
    this.router.navigate(['register']);
  }
}
