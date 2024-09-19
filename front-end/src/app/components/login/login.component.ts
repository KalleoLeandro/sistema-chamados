import { Component, OnInit } from '@angular/core';
import { Cripto } from 'src/app/models/Cripto';
import * as forge from 'node-forge';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { publicKey } from 'src/app/models/PublicKey';
import { Login } from 'src/app/models/Login';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public publicKey: string = publicKey;

  public token = sessionStorage.getItem('authorization') || "";
  public userName = sessionStorage.getItem('userName') || "";

  public loginForm: FormGroup = this.formBuilder.group({
    login: ['', Validators.required],
    senha: ['', Validators.required]
  });

  public invalido: boolean = false;

  public login: Login = { login: "", senha: "" };

  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private router: Router) {
    this.loginService.validarToken(this.token, this.userName).subscribe({
      next: (res) => {
        this.router.navigate(['/home']);
      }
    })
    this.router.navigate(['/']);
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
    this.loginService.login(this.hash).subscribe({
      next: (res) => {
        sessionStorage.setItem('authorization', res.body.token);
        sessionStorage.setItem('userName', res.body.userName);
        this.router.navigate(['/home']);
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
