import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { HomeComponent } from './components/home/home.component';
import { canActivate } from './guard/auth.guard';
import { CadastroUsuarioComponent } from './components/usuario/cadastro-usuario/cadastro-usuario.component';
import { ListarUsuarioComponent } from './components/usuario/listar-usuario/listar-usuario.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  // Redireciona o path vazio para o 'home' dentro de MainComponent
  {
    path: '', redirectTo: 'home', pathMatch: 'full'
  },
  {
    path: 'usuarios', redirectTo: 'home', pathMatch: 'full'
  },
  // Configuração do MainComponent com o canActivate
  {
    path: '', component: MainComponent, canActivate: [canActivate], children: [
      { path: 'home', component: HomeComponent },
      { path: 'usuarios', children: [
        { path: 'cadastro', component: CadastroUsuarioComponent },
        { path: 'cadastro/:id', component: CadastroUsuarioComponent },
        { path: 'lista', component: ListarUsuarioComponent }
      ]}
    ]
  },  
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
