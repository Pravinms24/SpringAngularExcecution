import { Component, OnInit } from "@angular/core";
// import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormArray, FormBuilder, FormControl, FormGroup, NgForm, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {

  registrationForm: FormGroup;
  constructor(private fb: FormBuilder,private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.registrationForm = this.fb.group({
      fullname: ["", [Validators.required, Validators.minLength(5)]],
      email: ["", [Validators.required, Validators.minLength(5)]],
      password: [null, Validators.required],
      confirmPassword: [null, Validators.required],
      phoneNumber: [null, Validators.required],
      enabled: [true],
      roles:this.fb.array([{'role':'ADMIN'}]),
  
      address: this.fb.group({
        city: [""],
        state: [""],
        country: [""],
        postalCode: [""],
      }),
    },
    //{ validator: this.ConfirmPasswordValidator("password", "confirmPassword")}
    { validator: this.checkPasswords}
    
    );

  }
  // registrationForm = new FormGroup({
  //   emailId: new FormControl(),
  //   password: new FormControl('',Validators.required),
  //   confirmPassword: new FormControl(),
  //   address: new FormGroup({
  //     city: new FormControl(),
  //     state: new FormControl(),
  //     country: new FormControl(),
  //     postalCode: new FormControl()
  //   }),
  // });


  // this.registrationForm = this.fb.group({
  //   fullname: ["", [Validators.required, Validators.minLength(5)]],
  //   emailId: ["", [Validators.required, Validators.minLength(5)]],
  //   password: [null, Validators.required],
  //   confirmPassword: [null, Validators.required],
  //   phoneNumber: [null, Validators.required],
  //   enabled: [true],

  //   address: this.fb.group({
  //     city: [""],
  //     state: [""],
  //     country: [""],
  //     postalCode: [""],
  //   }),
  // },
  // //{ validator: this.ConfirmPasswordValidator("password", "confirmPassword")}
  // { validator: this.checkPasswords}
  
  // );

  loadApiData() {
    this.registrationForm.patchValue({
      emailId: "mspravin7@gmail.com",
      password: "24719945.",
      confirmPassword: "24719945.",
      address: {
        city: "Nagercoil",
        state: "Tamil Nadu",
        country: "India",
        postalCode: "629801",
      },
    });
  }

  checkPasswords (group : FormGroup) {
    let pass = group.get('password').value;
    let confirmPass = group.get('confirmPassword').value
    return pass === confirmPass ? null : { passwordNotSame: true } 
  }

  RegisterUser(form: NgForm) {
    delete this.registrationForm.value.confirmPassword;
    console.log(form);

    this.authService.register(form)
    .subscribe(
      result => {
        if (result.email) {
          //localStorage.setItem('token', result.token)
          this.router.navigate(['login'])
        }
      }
    )
  }

  onReset() {
    this.registrationForm.reset()
  }

}
