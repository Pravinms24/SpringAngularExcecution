import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = localStorage.getItem('token');
    if (jwtToken) {
      request = request.clone({
        setHeaders : {
          'Authorization': 'Bearer ' + jwtToken
        }
      });
      
    }

    if (!request.headers.has('Context-Type')) {

      request = request.clone({
        setHeaders: {
          'content-type': 'application/json'
        }
      });

    }

    request = request.clone({
      headers: request.headers.set('Accept', 'application/json')
    });

    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) => {

        if (event instanceof HttpResponse) {
          console.log('event--->>>', event);
        }

        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        console.log(error);

        if (error.status === 401) {
          this.router.navigate(['login']);
        }
        if (error.status === 400) {
          alert(error.error);
        }

        return throwError(error)
      })
    )
  }

}
