import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Question } from '../../Models/Question';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-new-exam',
  standalone: true,
  imports: [
    CommonModule,
    MatButton,
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

  constructor(private formBuilder: FormBuilder) {
    this.examForm = this.formBuilder.group({
      category: ['', Validators.required],
      level: ['', Validators.required],
      question: ['', Validators.required],
      correct_answer: ['', Validators.required],
      feedback: [''],
    });
  }

  agregarPregunta() {
    this.preguntas.push(this.preguntaActual);
    this.preguntaActual = {
      id: '',
      category: '',
      level: '',
      question: '',
      answers: {},
      correct_answer: '',
      feedback: '',
    };
    this.examForm.reset();
  }

  editarPreguntaAnterior() {}

  enviar() {
    console.log(this.preguntas.toString());
  }
}
