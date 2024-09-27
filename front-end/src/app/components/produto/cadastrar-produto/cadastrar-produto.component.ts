import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastrar-produto',
  templateUrl: './cadastrar-produto.component.html',
  styleUrls: ['./cadastrar-produto.component.css']
})
export class CadastrarProdutoComponent {


  public resposta: string = "";
  public categoria: Array<string> = [];
  public medida: Array<string> = [];

  public token: string = sessionStorage.getItem('authorization') || "";

  public cadastroForm: FormGroup = this.formBuilder.group({
    nome: ['', Validators.required],
    precoCusto: ['', Validators.required],
    precoVenda: ['', Validators.required],
    quantidade: ['', Validators.compose([Validators.required, Validators.min(1)])],
    medida: ['', Validators.required],
    categoria: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder, private router: Router) {

  }

  public limparFormulario() {
    this.cadastroForm.reset();
    this.cadastroForm.patchValue({
      medida: '',
      categoria: ''
    });
  }

  public redirecionar() {
    if (!this.resposta.includes("Erro")) {
      this.router.navigate(['./']);
    }
  }

  public cadastrarProduto() {
        
  }

}
