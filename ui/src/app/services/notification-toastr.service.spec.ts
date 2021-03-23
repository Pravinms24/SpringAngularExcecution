import { TestBed } from '@angular/core/testing';

import { NotificationToastrService } from './notification-toastr.service';

describe('NotificationToastrService', () => {
  let service: NotificationToastrService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotificationToastrService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
