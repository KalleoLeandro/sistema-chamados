import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { HomeComponent } from './components/home/home.component';
import { canActivate } from './guard/auth.guard';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  {
    path: "", component: MainComponent, children: [
      { path: "home", component: HomeComponent }
    ],
    canActivate: [canActivate]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
