import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  private url: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public carregarEndereco(cep: string): Observable<any> {
    return this.http.get<any>(`https://viacep.com.br/ws/${cep.replace("-", "")}/json/`, { observe: 'response' });
  }

  public validarDataNascimento(dataNascimento: string, token: string): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'authorization': `${token}`,
      })
    }
    return this.http.post<any>(`${this.url}valida-data-nascimento`, { dataNascimento }, httpOptions);
  }
}