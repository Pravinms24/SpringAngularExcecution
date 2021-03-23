import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationToastrService } from 'src/app/services/notification-toastr.service';
import { UserseriviceService } from 'src/app/services/userserivice.service';

@Component({
  selector: 'app-viewuser',
  templateUrl: './viewuser.component.html',
  styleUrls: ['./viewuser.component.css']
})
export class ViewuserComponent implements OnInit {

  action: string;
  user: any = {};

  constructor( private route:ActivatedRoute ,private router: Router, private userService:UserseriviceService, private notification: NotificationToastrService) { }

  ngOnInit(): void {

    this.route.queryParams
    .subscribe(
      params => 
      {
        this.action = params['action'];
        this.user.id = params['id'];
      }
    )
    
    this.getById()
  }

  getById() {
    this.userService.getUserById(this.user.id)
      .subscribe(
        data => {
          if (data.status == true) {
            this.user = data.responseObject;
            this.user.password = "";
          } else {
            this.notification.showToast('error', data.responseDescription);
          }
        }
      )
  }

}
