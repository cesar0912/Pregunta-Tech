import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  ValidatorFn,
  AbstractControl,
  ValidationErrors,
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
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ChangeDetectorRef } from '@angular/core';
import { HeaderComponent } from '../header/header.component';

interface User {
  email: string;
  password: string;
  name: string;
  surname: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
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
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private readonly router: Router,
    private readonly snackBar: MatSnackBar,
    private cdRef: ChangeDetectorRef
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, this.passwordValidator()]],
      name: ['', [Validators.required, this.nameValidator()]],
      surname: ['', [Validators.required, this.nameValidator()]],
    });
  }

  ngOnInit() {}

  private passwordValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const passwordRegex =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
      return passwordRegex.test(control.value)
        ? null
        : { invalidPassword: true };
    };
  }

  private nameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const nameRegex = /^[A-Za-zÀ-ÖØ-öø-ÿ\s]{2,}$/;
      return nameRegex.test(control.value) ? null : { invalidName: true };
    };
  }

  public send(): void {}

  onSubmit(): void {
    if (this.registerForm.valid) {
      console.log(
        'Formulario válido. Enviar datos de inicio de sesión:',
        this.registerForm.value
      );
    } else {
      console.log('Formulario inválido. Comprueba los campos.');
    }
  }

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }
}
