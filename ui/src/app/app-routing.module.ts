import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ProductsComponent } from './components/products/products.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ChangepasswordComponent } from './components/auth/changepassword/changepassword.component';
import { ListcustomerComponent } from './components/customer/listcustomer/listcustomer.component';
import { ListuserComponent } from './components/user/listuser/listuser.component';
import { ViewuserComponent } from './components/user/viewuser/viewuser.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'changepassword', component: ChangepasswordComponent },
  { path: 'products', canActivate: [AuthGuard], component: ProductsComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'listuser', component: ListuserComponent },
  { path: 'listcustomer', component: ListcustomerComponent },
  { path: 'viewuser', component: ViewuserComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}

//Instead of adding all the routes we added in app module. we adding it in an array, adding this array in app.module declaration.
export const routingComponents = [LoginComponent, RegisterComponent, ProductsComponent, DashboardComponent, ChangepasswordComponent,
  ListuserComponent, ListcustomerComponent, ViewuserComponent]