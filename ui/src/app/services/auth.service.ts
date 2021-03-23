import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {GlobalConfig} from '../config/globalConfig'

//const backendUrl = "http://localhost:8085/"
// globalConfig : GlobalConfig
@Injectable(
  {
  providedIn: 'root'
}
)

export class AuthService {
  isLoggedIn = false;
  redirectUrl: string;

  constructor(private http: HttpClient , private globalConfig : GlobalConfig) { }

  login(user): Observable<any> {
    return this.http.post<any>(this.globalConfig.backendUrl + 'api/auth/login', user)
      .pipe(
        tap(_ => this.isLoggedIn = true),
        catchError(this.handleError('login', []))
      );
      
  }

  register(data: any): Observable<any> {
    //console.log("Ins"+data)
    return this.http.post<any>(this.globalConfig.backendUrl + 'api/auth/register', data)
      .pipe(
        tap(_ => this.isLoggedIn = true),
        catchError(this.handleError('register', []))
      );
      
  }

private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    console.error("Error Msg: "+error); // log to console instead
    this.log(`${operation} failed: ${error.message}`);

    return of(result as T);
  };
  
}

private log(message: string) {
  console.log(message);
}
getAppVersion() {
  return this.http.get<any>(this.globalConfig.backendUrl+'application/getversion')
}


}
