import {inject, TestBed} from '@angular/core/testing';

import {RestfulClientService} from './restful-client.service';
import {LoggingService} from './logging/logging.service';

describe('RestfulClientService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        RestfulClientService,
        LoggingService
      ]
    });
  });

  it('should be created', inject([RestfulClientService], (service: RestfulClientService) => {
    expect(service).toBeTruthy();
  }));
});
