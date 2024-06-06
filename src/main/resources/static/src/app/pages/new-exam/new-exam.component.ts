import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Question } from '../../Models/Question';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-new-exam',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    HeaderComponent,
  ],
  templateUrl: './new-exam.component.html',
  styleUrls: ['./new-exam.component.css'],
})
export class NewExamComponent {
  examForm: FormGroup;
  questionForm: FormGroup;
  preguntas: Question[] = [];
  preguntaActual: Question = {
    id: '',
    category: '',
    level: '',
    question: '',
    answers: {},
    correct_answer: '',
    feedback: '',
  };
  firstInfoChecked: boolean = false;
  categoryInput: string = '';
  levelInput: string = '';
  respuestaArray: number[] = [0, 1, 2, 3]; // Array para manejar dinámicamente los inputs de respuestas

  constructor(private formBuilder: FormBuilder) {
    this.examForm = this.formBuilder.group({
      category: ['', Validators.required],
      level: ['', Validators.required],
    });

    this.questionForm = this.formBuilder.group({
      question: ['', Validators.required],
      correct_answer: ['', Validators.required],
      feedback: [''],
    });

    this.respuestaArray.forEach((index) => {
      this.questionForm.addControl(`answer_${index}`, new FormControl(''));
    });
  }

  saveCategoryLevel() {
    this.categoryInput = this.examForm.value.category;
    this.levelInput = this.examForm.value.level;
    this.firstInfoChecked = true;
  }

  agregarPregunta() {
    const formValues = this.questionForm.value;
    const answers: { [key: string]: string } = {};
    this.respuestaArray.forEach((i) => {
      const key = `answer_${i}`;
      if (formValues[key]) {
        answers[key] = formValues[key];
      }
    });

    this.preguntaActual = {
      id: this.generateRandomId(),
      category: this.categoryInput,
      level: this.levelInput,
      question: formValues.question,
      answers: answers,
      correct_answer: formValues.correct_answer,
      feedback: formValues.feedback,
    };

    this.preguntas.push(this.preguntaActual);

    this.preguntaActual = {
      id: '',
      category: this.categoryInput,
      level: this.levelInput,
      question: '',
      answers: {},
      correct_answer: '',
      feedback: '',
    };

    this.questionForm.reset();
    this.respuestaArray = [0, 1, 2, 3];
    this.respuestaArray.forEach((index) => {
      this.questionForm.addControl(`answer_${index}`, new FormControl(''));
    });
  }

  addRespuesta() {
    if (this.respuestaArray.length < 10) {
      const nextIndex = this.respuestaArray.length;
      this.respuestaArray.push(nextIndex);
      this.questionForm.addControl(`answer_${nextIndex}`, new FormControl(''));
    }
  }

  removeRespuesta() {
    if (this.respuestaArray.length > 3) {
      const lastIndex = this.respuestaArray.pop();
      if (lastIndex !== undefined) {
        this.questionForm.removeControl(`answer_${lastIndex}`);
      }
    }
  }

  generateRandomId() {
    return Math.random().toString(36).substr(2, 15);
  }

  editarPreguntaAnterior() {}

  enviar() {
    console.log(this.preguntas);
  }
}
