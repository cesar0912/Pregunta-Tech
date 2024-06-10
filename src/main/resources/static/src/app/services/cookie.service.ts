import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { DOCUMENT, isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class CookieService {
  private documentIsAccessible: boolean;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.documentIsAccessible = isPlatformBrowser(this.platformId);
  }

  getCookie(name: string): string | null {
    if (!this.documentIsAccessible) {
      return null;
    }

    const nameEQ = name + '=';
    const ca = this.document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) === ' ') c = c.substring(1, c.length);
      if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
  }

  setCookie(name: string, value: string, days: number): void {
    if (!this.documentIsAccessible) {
      return;
    }

    let expires = '';
    if (days) {
      const date = new Date();
      date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
      expires = '; expires=' + date.toUTCString();
    }
    this.document.cookie = name + '=' + (value || '') + expires + '; path=/';
  }

  deleteCookie(name: string): void {
    if (!this.documentIsAccessible) {
      return;
    }

    this.document.cookie = name + '=; Max-Age=-99999999;';
  }
}
