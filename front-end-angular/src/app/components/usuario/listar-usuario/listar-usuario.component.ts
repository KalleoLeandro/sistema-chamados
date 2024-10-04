import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CadastroService } from 'src/app/services/cadastro.service';
import { ListaService } from 'src/app/services/lista.service';

@Component({
  selector: 'app-listar-usuario',
  templateUrl: './listar-usuario.component.html',
  styleUrls: ['./listar-usuario.component.css']
})
export class ListarUsuarioComponent {

  public lista: any;

  public confirmacao: boolean = false;

  public resposta: string = "";

  public token: string = sessionStorage.getItem('authorization') || '';

  public userId:number | undefined;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private cadastroService: CadastroService, private listaService: ListaService) {
    this.listaService.listaUsuarios(this.token).subscribe({
      next: (res: any) => {
        this.lista = res.body;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  public editarUsuario(id: any) {
    this.router.navigate([`./usuarios/cadastro/${id}`]);
  }

  excluirUsuario(id: number) {
    this.confirmacao = true;
    this.userId = id;
    this.resposta = "Tem certeza que deseja excluir esse usuário? Esta ação é irreversível";
    document.getElementById('botaoModal')?.click();
  }

  public confirmar() {
    this.confirmacao = false;
    this.cadastroService.excluirUsuario(this.userId, this.token as string).subscribe({
      next: (res) =>{        
        this.resposta = "Usuário excluido com sucesso!";    
      },
      error: (err) =>{
        this.resposta = "Erro ao excluir usuário!";
        console.log(err);
      }
    });
  }

  public concluir() {
    window.location.reload();
  }
}
