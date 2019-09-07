import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AbonnementenComponent} from './abonnementen.component';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {VodagoneMaterialModule} from '../../modules/material.module';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {LoggingService} from '../../services/logging/logging.service';
import {AbonneeService} from '../../services/abonee/abonee.service';

describe('AbonnementenComponent', () => {
  let component: AbonnementenComponent;
  let fixture: ComponentFixture<AbonnementenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AbonnementenComponent
      ],
      imports: [
        VodagoneAngularModule,
        VodagoneMaterialModule
      ],
      providers: [
        AbonneeService,
        AbonnementenService,
        LoggingService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbonnementenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
