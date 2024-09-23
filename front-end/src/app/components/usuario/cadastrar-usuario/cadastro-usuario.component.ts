import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CadastroUsuarioService } from 'src/app/services/cadastro-usuario.service';
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

  

  constructor(private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute, private utilsService: UtilsService, private cadastroUsuarioService: CadastroUsuarioService){
    
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
    const dataNascimento = this.cadastroForm.controls['dataNascimento'].value;
    this.utilsService.validarDataNascimento(dataNascimento, this.token).subscribe({
      next: (res) => {
        this.dataInvalida = false;
      },
      error: (error) => {
        this.dataInvalida = true;
      }
    })
  }

  cpfValido(){        
    const cpf = this.cadastroForm.controls['cpf'].value.replaceAll(".", "").replaceAll("-", "");
    if (cpf.length === 11) {
      this.utilsService.validarCpf(cpf, this.token).subscribe({
        next: (res) =>{
          this.cpfInvalido = false;
        },
        error: (error) =>{
          this.cpfInvalido = true;
        }
      });
    } else {
      this.cpfInvalido = false;
    }        
  }

  cadastrarAtualizarUsuario(){
    this.cadastroUsuarioService.cadastrarAtualizarUsuario(this.cadastroForm, this.token as string).subscribe({
      next: (res) => {
        this.id != null && this.id != undefined ? this.resposta = "Dados atualizados com sucesso!" : this.resposta = "Dados cadastrados com sucesso!";
        document.getElementById("botaoModal")?.click();
      },
      error: (err) => {        
        this.id != null && this.id != undefined ? this.resposta = "Erro ao atualizar o usuário!" : this.resposta = "Erro ao cadastrar o usuário!";
        document.getElementById("botaoModal")?.click();        
      }
    });
  }

  public limparFormulario() {
    this.cadastroForm.reset();
    this.cadastroForm.patchValue({
      sexo: 'm'
    })
  }

  public redirecionar() {
    if (!this.resposta.includes("Erro")) {
      this.router.navigate(['/']);
    }
  }
}


