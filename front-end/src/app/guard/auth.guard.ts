import { inject } from '@angular/core';
import { CanActivateFn, CanActivateChildFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from "../services/auth.service";
import { catchError, map } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const authService = inject(AuthenticationService);
  const router = inject(Router);

  return authService.checkLogin().pipe(
    map((isAuthenticated) => {
      if (isAuthenticated) {
        return true;
      } else {
        // Redireciona para a rota de login sem navegação explícita
        return router.createUrlTree(['login']);
      }
    }),
    catchError(() => {
      // Se houver erro (token inválido), redireciona para login
      return of(router.createUrlTree(['login']));
    })
  );
};
