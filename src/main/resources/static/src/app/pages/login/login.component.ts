import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { LoginService } from '../../services/login.service';
import { User } from '../../Models/User';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    MatCardModule,
    MatSelectModule,
    ReactiveFormsModule,
    HeaderComponent,
  ],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private readonly router: Router,
    private readonly loginService: LoginService,
    private snackBar: MatSnackBar,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  ngOnInit() {}

  onSubmit(): void {
    this.checkLogin();
  }

  private checkLogin(): void {
    if (this.loginForm.valid) {
      const user: User = this.loginForm.value;
      this.loginService.sendRegister(user).subscribe({
        next: (response) => {
          console.log('Login exitoso:', response);
          this.snackBar.open('Login exitoso', 'Cerrar', {
            duration: 3000,
          });
          this.updateLanding(response);
        },
        error: (error) => {
          console.error('Error en el Login:', error);
          this.snackBar.open('Error en el Login', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    } else {
      console.log('Formulario inválido. Comprueba los campos.');
    }
  }

  updateLanding(token: string) {
    this.authService.setToken(token);
    console.log(this.authService.getToken);
    this.navigateLanding();
  }

  public navigateRegister(): void {
    this.router.navigate(['register']);
  }

  public navigateResetPassword(): void {
    this.router.navigate(['reset-password']);
  }

  public navigateLanding(): void {
    this.router.navigate(['landing']);
  }
}
