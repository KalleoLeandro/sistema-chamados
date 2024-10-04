import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private url: string = environment.apiUrl;

  
  constructor(private http: HttpClient) {}

  checkLogin(): Observable<boolean> {
    const token = sessionStorage.getItem('authorization');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': `${token}`,
      })
    }
    if (token) {
      return this.http.post<boolean>(`${this.url}valida-token`, null, httpOptions);
    } else {
      return of(false);
    }
  }
}
