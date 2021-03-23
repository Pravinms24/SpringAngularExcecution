import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NotificationToastrService } from 'src/app/services/notification-toastr.service';
import { UserseriviceService } from 'src/app/services/userserivice.service'

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {
  action: string;
  user: any = {};
  errorMap: Map<string, string> = new Map<string,string>();

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserseriviceService, private notification: NotificationToastrService) { }

  ngOnInit() {
    this.route
      .queryParams
      .subscribe(params => {
        this.action = params['action'];
        this.user.id = params['id'];
      })
    this.getById();
  }

  getById() {
    console.log(this.action, this.user);
    this.userService.getUserById(this.user.id)
      .subscribe(
        data => {
          if (data.status == true) {
            console.log(data.responseObject)
            this.user = data.responseObject;
            this.user.password = "";
          } else {
            this.notification.showToast('error', data.responseDescription);
          }
        }
      )
  }

  validatePassword(args: any) {

    this.errorMap = new Map<string,string>();
    
    console.log(this.user.password)
    if(args == 0 || args == 1) {
      if(this.user.password == null || this.user.password == undefined || this.user.password == "") {
        this.errorMap.set('password','password required');
        return false;
      }
    }
    if(args == 0 || args == 2) {
      if(this.user.confirmPassword == null || this.user.confirmPassword == undefined || this.user.confirmPassword == "") {
        this.errorMap.set('confirmPassword','confirmPassword required');
        return false;
      }
    }
    if(args==0) {
      if(this.user.password != this.user.confirmPassword) {
        this.errorMap.set('confirmPassword','Password and confirm password not matching');
        return false;
      }
    }
    return true;
  }

  changePassword(){
    console.log('user', this.user)
    if(!this.validatePassword(0)) {
      return;
    }
    this.userService.changePassword(this.user)
    .subscribe(
      result => {
        if(result.status == true) {
          this.notification.showToast('sucess',result.responseDescription);
          this.router.navigate(['/listuser'])
        } else {
          this.notification.showToast('error',result.responseDescription);
        }
      }
    )
  }

  back(){
    
  }

}
