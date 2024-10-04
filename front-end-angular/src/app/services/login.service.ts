import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cripto } from '../models/Cripto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string = environment.apiUrl;

  constructor(private http:HttpClient) { }

  public login(hash: Cripto): Observable<any> {
    return this.http.post<any>(`${this.url}login`, hash, { observe: 'response' });
  }

  public validarToken(token: string): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': `${token}`,
      })
    }
    return this.http.post<any>(`${this.url}valida-token`, { token }, httpOptions);
  }
}
