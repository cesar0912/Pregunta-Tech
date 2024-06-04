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
  questionsMocked = [
    {
      id: '89',
      category: 'javascript',
      level: 'facil',
      question:
        'const person = {name:"John", age:31, city:"New York"};... ¿cuál es la forma correcta de acceder a los valores?',
      answers: {
        answer_a: 'person.name',
        answer_b: 'person["name"]',
        answer_c: 'Ambas son correctas',
      },
      correct_answer: 'answer_c',
    },
    {
      id: '325',
      category: 'javascript',
      level: 'facil',
      question:
        '¿Qué sentencia puede tomar una sola expresión como entrada y luego buscar a través de un número de opciones hasta que se encuentre una que coincida con ese valor?',
      answers: {
        answer_a: 'else',
        answer_b: 'when',
        answer_c: 'switch',
        answer_d: 'if',
      },
      correct_answer: 'answer_c',
    },
    {
      id: '358',
      category: 'javascript',
      level: 'facil',
      question:
        '¿Qué método de la API del navegador se utiliza para hacer una petición HTTP de forma nativa?',
      answers: {
        answer_a: 'fetch("https://some-url-here.com")',
        answer_b: 'axios.get("https://some-url-here.com")',
        answer_c: 'makeRequest("https://some-url-here.com")',
      },
      correct_answer: 'answer_a',
    },
    {
      id: '66',
      category: 'javascript',
      level: 'facil',
      question: 'Cómo insertar un comentario que tiene más de una línea?',
      answers: {
        answer_a: '/*Este comentario tiene más de una línea.*/',
        answer_b: '<!--Este comentario tiene más de una línea.-->',
        answer_c: '//Este comentario tiene más de una línea.//',
      },
      correct_answer: 'answer_a',
      feedback:
        ' Los comentarios comienzan con /* y terminan con */ . Cualquier texto entre /* y */ serán ignorados por JavaScript.',
    },
    {
      id: '78',
      category: 'javascript',
      level: 'facil',
      question:
        '¿Cuál es la forma correcta de incluir un archivo JS externo en HTML?',
      answers: {
        answer_a: '<script src="main.js">',
        answer_b: '<script href="main.js">',
        answer_c: '<script name="main.js">',
      },
      correct_answer: 'answer_a',
      feedback:
        "La etiqueta HTML <script></script> solo puede utilizar el atributo 'src', 'href' es usado en enlaces con etiqueta <a></a>.",
    },
  ];
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
    private readonly categoryCacheService: CategoryCacheService
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
    console.log('Url desde generar url: ' + url);
    return url;
  }

  public onSubmit(): void {
    const apiUrl = this.createTestRequest();

    this.router.navigate(['test'], {
      state: { questions: this.questionsMocked },
    });

    /*    TODO Pendiente de implementar para conectar al Back por fallo en política CORS
        this.preguntaApiQuestionsService.getQuestions(apiUrl).subscribe({
        next: (response: { questions: any }) => {
          this.router.navigate(['test'], {
            state: { questions: response.questions },
          });
        },
        error: (error: any) => {
          console.error('Error fetching questions', error);
        },
      }); */
  }

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }

  public navigateRegister(): void {
    this.router.navigate(['register']);
  }
}
