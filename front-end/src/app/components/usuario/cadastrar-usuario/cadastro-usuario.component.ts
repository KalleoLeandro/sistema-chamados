import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.css']
})
export class CadastroUsuarioComponent {

  public cadastroForm: FormGroup = this.formBuilder.group({
    id: [{value: '', disabled: true}],
    nome: ['', Validators.required],
    cpf: ['', Validators.required],
    dataNascimento: ['', Validators.required],
    sexo: ['m', Validators.required],
    cep: ['', Validators.required],
    rua: [{ value: '', disabled: true }, Validators.required],
    numero: ['', Validators.required],
    bairro: [{ value: '', disabled: true }, Validators.required],
    cidade: [{ value: '', disabled: true }, Validators.required],
    uf: [{ value: '', disabled: true }, Validators.required],
    telefone: ['', Validators.required],
    celular: ['', Validators.required],
    email: ['', Validators.required],
    login: ['', Validators.required],
    senha: ['', Validators.required],
    perfil: ['', Validators.required]
  });

  public perfil: Array<string> = ["dev", "user", "adm"];
  public resposta: string = "";

  public estados: Array<string> = [];

  public cpfInvalido: boolean = false;
  public dataInvalida: boolean = false;  
  public hoje: string = "";
  public id;

  public token: string = sessionStorage.getItem('authorization') || '';

  

  constructor(private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute, private utilsService: UtilsService){
    
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if(this.id != null && this.id != undefined){
      this.carregarUsuario();
    }

    let dataHoje = new Date();

    let ano = dataHoje.getFullYear();
    let mes = ('0' + (dataHoje.getMonth() + 1)).slice(-2);
    let dia = ('0' + dataHoje.getDate()).slice(-2);
    this.hoje = `${ano}-${mes}-${dia}`;
  }

  
  carregarUsuario() {

  }

  carregarEndereco(){
    this.utilsService.carregarEndereco(this.cadastroForm.controls['cep'].value).subscribe({
      next: (response)=>{
        this.cadastroForm.controls['rua'].patchValue(response.body.logradouro);
        this.cadastroForm.controls['bairro'].patchValue(response.body.bairro);
        this.cadastroForm.controls['cidade'].patchValue(response.body.localidade);
        this.cadastroForm.controls['uf'].patchValue(response.body.uf);
      },
      error: (error)=>{
        console.log(error);
      }
    })
  }

  dataNascimentoValida() {
    debugger;
    const dataNascimento = this.cadastroForm.controls['dataNascimento'].value;
    this.utilsService.validarDataNascimento(dataNascimento, this.token).subscribe({
      next: (res) => {
        this.dataInvalida = false;
      },
      error: (err) => {
        this.dataInvalida = true;
      }
    })
  }

}


