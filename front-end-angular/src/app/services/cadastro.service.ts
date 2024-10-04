import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {
  

  private url: string = environment.apiUrl;
  
  constructor(private http: HttpClient) {}

  public httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public cadastrarAtualizarUsuario(formulario:FormGroup, token:string):Observable<string>{
    this.httpOptions.headers = this.httpOptions.headers.set('authorization', `${token}`);
    return this.http.post<string>(`${this.url}gravar-usuario`, formulario.getRawValue() , this.httpOptions);
  }

  public excluirUsuario(userId: number | undefined, token: string) {
    this.httpOptions.headers = this.httpOptions.headers.set('authorization', `${token}`);
    return this.http.delete<string>(`${this.url}excluir-usuario-por-id/${userId}`, this.httpOptions);
  }
}
