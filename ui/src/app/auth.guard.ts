import { state } from '@angular/animations';
import { SummaryResolver } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor (private authService: AuthService, private router: Router) {}
  user: any = {};

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean{
    const url: string = state.url
      console.log('can act')
    return this.checkLoggedIn(url);
  }

  checkLoggedIn (url: string) {
    this.user = localStorage.getItem('username')
    if (localStorage.getItem('username')) {
      if(url.includes('/login') || url.includes('/register')){
        return false
   }

   if (localStorage.getItem('userrole')=='ADMIN') {
     
     if(url.includes('/products') || url.includes('/dashboard')) {
       return true;
     }
   }
   return false;

   
    }

    this.router.navigate(['/login'])
    // if (this.authService.isLoggedIn) {
    //   return true
    // }
    // this.authService.redirectUrl = url
    // this.router.navigate(['/login']);
    // return false;
  }
  
}
