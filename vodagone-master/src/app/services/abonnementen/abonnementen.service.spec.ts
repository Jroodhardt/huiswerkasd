import {inject, TestBed} from '@angular/core/testing';

import {AbonnementenService} from './abonnementen.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {LoggingService} from '../logging/logging.service';

describe('AbonnementenService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        AbonnementenService,
        LoggingService
      ]
    });
  });

  it('should be created', inject([AbonnementenService], (service: AbonnementenService) => {
    expect(service).toBeTruthy();
  }));
});
