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
import { MatSelectModule } from '@angular/material/select';
import { HeaderComponent } from '../header/header.component';
import { ExamService } from 'src/app/services/exam.service';
import { Exam } from 'src/app/Models/Exam';
import { QuestionExam } from 'src/app/Models/QuestionExam';
import { Question, clearQuestionInterface } from 'src/app/Models/Question';

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
    MatSelectModule,
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
  respuestaArray: number[] = [0, 1, 2, 3];

  constructor(
    private formBuilder: FormBuilder,
    private readonly examService: ExamService
  ) {
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
    this.categoryInput = this.examForm.value.category.toLowerCase();
    this.levelInput = this.examForm.value.level.toLowerCase();
    this.firstInfoChecked = true;
  }

  addQuestionToExam() {
    const formValues = this.questionForm.value;
    const answers: { [key: string]: string } = {};
    this.respuestaArray.forEach((i) => {
      const key = `answer_${String.fromCharCode(97 + i)}`;
      if (formValues[`answer_${i}`]) {
        answers[key] = formValues[`answer_${i}`];
      }
    });

    const correctAnswerKey = formValues.correct_answer;

    this.preguntaActual = {
      id: this.generateRandomId(),
      category: this.categoryInput,
      level: this.levelInput,
      question: formValues.question,
      answers: answers,
      correct_answer: correctAnswerKey,
      feedback: formValues.feedback,
    };

    this.preguntas.push(this.preguntaActual);

    this.preguntaActual = clearQuestionInterface(
      this.preguntaActual,
      this.categoryInput,
      this.levelInput
    );

    this.questionForm.reset();
    this.respuestaArray = [0, 1, 2, 3];
    this.respuestaArray.forEach((index) => {
      this.questionForm.addControl(`answer_${index}`, new FormControl(''));
    });
  }

  addAnswer() {
    if (this.respuestaArray.length < 10) {
      const nextIndex = this.respuestaArray.length;
      this.respuestaArray.push(nextIndex);
      this.questionForm.addControl(`answer_${nextIndex}`, new FormControl(''));
    }
  }

  removeAnswer() {
    if (this.respuestaArray.length > 3) {
      const lastIndex = this.respuestaArray.pop();
      if (lastIndex !== undefined) {
        this.questionForm.removeControl(`answer_${lastIndex}`);
      }
    }
  }

  generateRandomId(): string {
    return Math.random().toString(36).substr(2, 15);
  }

  transformQuestionsToQuestionExam(questions: Question[]): QuestionExam[] {
    return questions.map((pregunta) => {
      const answersArray: string[] = Object.values(pregunta.answers);

      return {
        id: parseInt(pregunta.id),
        category: pregunta.category,
        level: pregunta.level,
        question: pregunta.question,
        answers: answersArray,
        correct_answer: this.transformCorrectAnswer(pregunta.correct_answer),
        feedback: pregunta.feedback,
      };
    });
  }

  private transformCorrectAnswer(correctAnswer: string): string {
    return correctAnswer
      .split('_')
      .map((part, index) =>
        index === 1 ? String.fromCharCode(96 + parseInt(part, 10)) : part
      )
      .join('_');
  }

  sendExam() {
    const exam: Exam = {
      questions: this.transformQuestionsToQuestionExam(this.preguntas),
    };

    this.examService.saveExam(exam).subscribe({
      next: (response) => {
        if (response) {
          console.log('Exam saved successfully:', response);
        } else {
          console.error('Error saving exam: Response is empty or null');
        }
      },
      error: (error) => {
        console.error('Error saving exam:', error);
      },
    });
  }
}
