import { NextExamService } from './../../services/next-exam.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnswersService } from '../../services/answer.service';
import { MatCardModule } from '@angular/material/card';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-final-exam-view',
  templateUrl: './final-exam-view.component.html',
  styleUrls: ['./final-exam-view.component.css'],
  standalone: true,
  imports: [MatCardModule, HeaderComponent],
})
export class FinalExamViewComponent implements OnInit {
  score: number = 0;
  totalQuestions: number = 0;
  porcentajeAciertos: number = 0;
  mensaje: string = '';

  constructor(
    private readonly router: Router,
    private answersService: AnswersService,
    private NextExamService: NextExamService
  ) {}

  ngOnInit() {
    this.score = this.getScore();
    this.totalQuestions = this.getAmount();
    this.porcentajeAciertos = this.calcularPorcentajeAciertos();
    this.mensaje = this.obtenerMensaje(this.porcentajeAciertos);
  }
  repeatExam() {
    this.answersService.resetExam();
  }
  nextQuestion() {
    this.NextExamService.nextExam();
  }

  navigateLandingAndResetExam() {
    this.answersService.setExamFinished(false);
    this.router.navigate(['landing']);
  }

  getScore(): number {
    return this.answersService.getScore();
  }

  getAmount(): number {
    return this.answersService.getAmount();
  }

  calcularPorcentajeAciertos(): number {
    return this.totalQuestions > 0
      ? (this.score / this.totalQuestions) * 100
      : 0;
  }

  obtenerMensaje(porcentaje: number): string {
    if (porcentaje === 100) {
      return '¡Perfecto! ¡Todas las respuestas son correctas!';
    } else if (porcentaje >= 80) {
      return '¡Excelente trabajo! La mayoría de tus respuestas son correctas.';
    } else if (porcentaje >= 50) {
      return 'Buen intento, sigue practicando.';
    } else {
      return 'Sigue practicando, puedes mejorar.';
    }
  }
}
