import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  FormArray,
} from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    HeaderComponent,
  ],
})
export class TestComponent implements OnInit {
  questions = [
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

  currentQuestionIndex: number = 0;
  score: number = 0;
  testForm!: FormGroup;
  answersSelected: boolean = false;

  constructor(private fb: FormBuilder) {
    this.testForm = this.fb.group({
      answers: this.fb.array([]),
    });
  }

  ngOnInit() {
    this.loadQuestion();
  }

  get answers(): FormArray {
    return this.testForm.get('answers') as FormArray;
  }

  loadQuestion(): void {
    const currentQuestion = this.questions[this.currentQuestionIndex];
    this.answers.clear();
    for (const key in currentQuestion.answers) {
      this.answers.push(this.fb.control(false));
    }
  }

  selectAnswer(index: number): void {
    const control = this.answers.at(index);
    control.setValue(!control.value);
  }

  onSubmit(): void {
    const currentQuestion = this.questions[this.currentQuestionIndex];
    const selectedAnswers = this.testForm.value.answers
      .map((checked: boolean, i: number) =>
        checked ? `answer_${String.fromCharCode(97 + i)}` : null
      )
      .filter((v: string | null) => v !== null);
    const correctAnswers = [currentQuestion.correct_answer];

    const allCorrect = selectedAnswers.every((answer: string) =>
      correctAnswers.includes(answer)
    );
    const allSelectedCorrect = correctAnswers.every((answer) =>
      selectedAnswers.includes(answer)
    );

    if (allCorrect && allSelectedCorrect) {
      this.score++;
    }

    this.answersSelected = true;
  }

  nextQuestion(): void {
    this.answersSelected = false;
    if (this.currentQuestionIndex < this.questions.length - 1) {
      this.currentQuestionIndex++;
      this.loadQuestion();
      this.testForm.reset();
    } else {
      // Test finished logic here
    }
  }
}
