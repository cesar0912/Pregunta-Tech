import { Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { TestComponent } from './pages/test/test.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { ViewExamsComponent } from './pages/view-exams/view-exams.component';
import { NewExamComponent } from './pages/new-exam/new-exam.component';
import { FinalExamViewComponent } from './pages/final-exam-view/final-exam-view.component';

export const routes: Routes = [
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'test', component: TestComponent },
  { path: 'landing', component: LandingComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'view-exams', component: ViewExamsComponent },
  { path: 'new-exam', component: NewExamComponent },
  { path: 'final-view-exam', component: FinalExamViewComponent },
  { path: 'next-exam', component: TestComponent },
];
