import {inject, TestBed} from '@angular/core/testing';

import {AbonneeService} from './abonee.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {LoggingService} from '../logging/logging.service';

describe('AbonneeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        AbonneeService,
        LoggingService
      ]
    });
  });

  it('should be created', inject([AbonneeService], (service: AbonneeService) => {
    expect(service).toBeTruthy();
  }));
});
