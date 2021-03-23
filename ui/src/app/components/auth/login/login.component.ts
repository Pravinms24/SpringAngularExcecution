import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
//import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

import { NotificationToastrService } from 'src/app/services/notification-toastr.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  user: any = {};
  applicationVersion: string="";
  // loginForm: FormGroup;
  // email = '';
  // password = '';
  // //matcher = new MyErrorStateMatcher();
  // isLoadingResults = false;

  constructor(private authService: AuthService, private router: Router,private notification: NotificationToastrService) { }
  

  ngOnInit() {
    this.getAppVersion();
  }

  registrationForm = new FormGroup({
    email: new FormControl('admin@gmail.com'),
    password: new FormControl('password')

  });

  submitLoginForm(form: NgForm) {
    localStorage.clear()
    this.user.email = this.registrationForm.value['email']
    this.user.password = this.registrationForm.value['password']
    this.authService.login(this.user)
    .subscribe(
      result => {
        if (result.status == true) {
          localStorage.setItem('token', result.responseObject.jwt);
          localStorage.setItem('username', result.responseObject.userName);
          localStorage.setItem('userrole', result.responseObject.role.role);
          localStorage.setItem('currentUser', JSON.stringify(result.responseObject));
          this.router.navigate(['dashboard']);
          this.notification.showToast('sucess',result.responseDescription);
        } else {
          this.notification.showToast('error',result.responseDescription)
        }
      }
    )
  }

  getAppVersion() {
    this.authService.getAppVersion()
    .subscribe(
      result => {
        this.applicationVersion = result.responseObject
        localStorage.setItem('applicationVersion',result.responseObject);
      }
    )
    
  }

}
