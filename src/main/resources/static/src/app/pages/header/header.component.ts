import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [CommonModule, MatToolbarModule, MatIconModule],
})
export class HeaderComponent implements OnInit {
  constructor(private readonly router: Router) {}

  ngOnInit() {}

  public navigateLogin(): void {
    this.router.navigate(['login']);
  }
  navigateLanding() {
    this.router.navigate(['landing']);
  }
}
