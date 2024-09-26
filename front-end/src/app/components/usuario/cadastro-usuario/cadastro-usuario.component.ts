import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CadastroService } from 'src/app/services/cadastro.service';
import { ListaService } from 'src/app/services/lista.service';
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
  public id:number;

  public token: string = sessionStorage.getItem('authorization') || '';

  

  constructor(private formBuilder: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute, private utilsService: UtilsService, private cadastroService: CadastroService, private listaService: ListaService){
    
    this.id = this.activatedRoute.snapshot.paramMap.get('id') as unknown as number;
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
    this.listaService.buscarUsuarioPorId(this.id, this.token).subscribe({
      next: (response) => {
        this.cadastroForm.controls['id'].patchValue(response.id);
        this.cadastroForm.controls['nome'].patchValue(response.nome);
        this.cadastroForm.controls['cpf'].patchValue(response.cpf);
        this.cadastroForm.controls['dataNascimento'].patchValue(this.formataData(response.data_nascimento));
        this.cadastroForm.controls['sexo'].patchValue(response.sexo);
        this.cadastroForm.controls['cep'].patchValue(response.cep);
        this.cadastroForm.controls['rua'].patchValue(response.rua);
        this.cadastroForm.controls['numero'].patchValue(response.numero);
        this.cadastroForm.controls['bairro'].patchValue(response.bairro);
        this.cadastroForm.controls['cidade'].patchValue(response.cidade);
        this.cadastroForm.controls['uf'].patchValue(response.uf);
        this.cadastroForm.controls['telefone'].patchValue(response.telefone);
        this.cadastroForm.controls['celular'].patchValue(response.celular);
        this.cadastroForm.controls['email'].patchValue(response.email);        
        this.cadastroForm.controls['perfil'].patchValue(response.perfil);
      },
      error: (error) => {
        console.log(error);
      }
    })
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
    this.cadastroService.cadastrarAtualizarUsuario(this.cadastroForm, this.token as string).subscribe({
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

  public formataData(data: string): string {
    const dateStr = data;
    const date = new Date(dateStr);
    const formattedDate = date.toISOString().substring(0, 10);    
    return formattedDate;
  }
}


