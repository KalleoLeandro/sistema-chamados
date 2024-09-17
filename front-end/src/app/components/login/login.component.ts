import { Component } from '@angular/core';
import { Cripto } from 'src/app/model/Cripto';
import * as forge from 'node-forge';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { publicKey } from 'src/app/model/PublicKey';
import { Login } from 'src/app/model/Login';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public publicKey: string = publicKey;

  public loginForm: FormGroup = this.formBuilder.group({
    login: ['', Validators.required],
    senha: ['', Validators.required]
  });

  public invalido: boolean = false;

  public login: Login = { login: "", senha: "" };

  constructor(private formBuilder: FormBuilder, private loginService:LoginService, private router:Router) {

  }

  public pubKey: any = forge.pki.publicKeyFromPem(this.publicKey);
  public encryptText: string = '';
  public hash: Cripto = {
    hash: ''
  };

  public change() {
    if (this.loginForm.controls['login'].value != '' || this.loginForm.controls['senha'].value != '') {
      this.invalido = false;
    }
  }

  public logar() {
    this.login = {
      login: this.loginForm.controls['login'].value,
      senha: this.loginForm.controls['senha'].value
    }

    const publicKeyForge = forge.pki.publicKeyFromPem(this.publicKey);
    const encryptedData = forge.util.encode64(publicKeyForge.encrypt(JSON.stringify(this.login)));
    this.hash.hash = encryptedData;
    alert(this.hash.hash);
    this.loginService.login(this.hash).subscribe({
      next: (res) => {
        sessionStorage.setItem('authorization', res.body.token);
        this.router.navigate(['./sistema']);
      },
      error: (err) => {
        if (err.status === 401) {
          this.loginForm.patchValue({
            usuario: '',
            senha: ''
          })
          this.invalido = true;
        }
      }
    });
  }
}
