import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [CommonModule, MatToolbarModule, MatIconModule, MatMenuModule],
})
export class HeaderComponent implements OnInit {
  isLoggedIn: boolean = false;

  constructor(
    private readonly router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.checkLoginOnInit();
  }

  private checkLoginOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }

  public navigateLanding(): void {
    this.router.navigate(['landing']);
  }

  public navigateNewExam(): void {
    this.router.navigate(['new-exam']);
  }

  public navigateViewExams(): void {
    this.router.navigate(['user-exams']);
  }

  public logout(): void {
    this.authService.clearToken();
    this.isLoggedIn = false;
    this.router.navigate(['landing']);
  }
}
