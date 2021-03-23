import { Component, OnInit } from '@angular/core';
import { UserseriviceService } from 'src/app/services/userserivice.service';
import { Pagenation } from 'src/app/dto/Pagenation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnInit {
  user: any = {};
  userList: any = [];
  requestObject: any = {};

  //pagination elements
  pagenation: Pagenation;
  page: number = 1;
  total: number = 0;
  itemPerPage: number = 10;

  //sorting order
  order: string = 'id';
  reverse: boolean = true;
  orderType: string = "desc";

  headElements = ['Email','Name', 'Contact', 'Active'];

  constructor(private userService: UserseriviceService, private router: Router) { }

  ngOnInit(): void {
    this.requestObject = {};
    this.requestObject.fullname = "";
    console.log('ng')
    this.getUsers();
  }

  getUsers() {
    console.log(this.requestObject.fullname)
    this.pagenation = new Pagenation(this.page - 1, this.itemPerPage, this.order, this.orderType, this.requestObject)
    console.log(this.pagenation)
    this.userService.getUsers(this.pagenation)
      .subscribe(
        result => {
          console.log(result);
          if (result.status == true) {
            this.userList = result.responseObject.content;
            this.total = result.responseObject.totalElements;
          }
        }
      )
  }

  pageChanged($event) {
    this.page = $event;
    this.getUsers();

  }

  view(userId: any) {
    this.router.navigate(['/viewuser'], { queryParams: { action: 'viewuser',id:userId } } )
  }


}
