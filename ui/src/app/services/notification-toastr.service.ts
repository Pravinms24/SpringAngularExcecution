import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationToastrService {

  constructor(private toastr: ToastrService) { }

  showToast(type:string, message:string) {
    if(type === 'sucess'){
      this.toastr.success(message);
    } else if (type === 'error') {
      this.toastr.error(message)
    }
    
  }
}
