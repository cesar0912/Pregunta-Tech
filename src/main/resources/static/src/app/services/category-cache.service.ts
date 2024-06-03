import { Injectable } from '@angular/core';
import { Category } from './pregunta-api-categories.service';

@Injectable({
  providedIn: 'root',
})
export class CategoryCacheService {
  private categories: Category[] = [];

  setCategories(categories: Category[]): void {
    this.categories = categories;
  }

  getCategories(): Category[] {
    return this.categories;
  }

  clearCache(): void {
    this.categories = [];
  }
}
