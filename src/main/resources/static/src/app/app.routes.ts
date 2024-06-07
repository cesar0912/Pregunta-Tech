import { Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { TestComponent } from './pages/test/test.component';
import { ResetPasswordComponent } from './pages/reset-password/reset-password.component';
import { UserExamsComponent } from './pages/user-exams/user-exams.component';
import { NewExamComponent } from './pages/new-exam/new-exam.component';

export const routes: Routes = [
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'test', component: TestComponent },
  { path: 'landing', component: LandingComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'user-exams', component: UserExamsComponent },
  { path: 'new-exam', component: NewExamComponent },
];
