import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {GlobalConfig} from '../config/globalConfig'

@Injectable({
  providedIn: 'root'
})
export class UserseriviceService {

  constructor(private http: HttpClient,private globalConfig:GlobalConfig) { }

  getUserById(id: string) {
    return this.http.get<any>(this.globalConfig.backendUrl+'user/getById/'+id);
  }
  changePassword(user: any) {
    return this.http.post<any>(this.globalConfig.backendUrl+'user/changePassword',user);
  }
  getUsers(pagenationObj: any){
    return this.http.post<any>(this.globalConfig.backendUrl+'user/getallusers',pagenationObj)
  }

  
}
