import { Component, OnInit } from '@angular/core';
import{ Router }  from'@angular/router'
import { NotificationToastrService } from 'src/app/services/notification-toastr.service';

@Component({
  selector: 'layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  applicationVersion: any;
  user: any = {};
  userName : any;

  constructor(private router: Router,private notification: NotificationToastrService) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.user)
    this.applicationVersion = localStorage.getItem('applicationVersion');
    this.userName = this.titleCaseWord(this.user.userName);
  }

  titleCaseWord(name: string) {
    if (!name) return name;
    if (name.length>7) {
      let subsName= name.substring(0,5);
      return subsName[0].toUpperCase() + subsName.substr(1).toLowerCase()+"..";
    }
    return name[0].toUpperCase() + name.substr(1).toLowerCase();
  }

  resetPassword() {
    this.router.navigate(['/changepassword'] ,{ queryParams: { action: 'changepassword',id:this.user.userId } })
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['/login']);
  }

}
