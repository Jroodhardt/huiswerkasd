import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AbonnementComponent} from './abonnement.component';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {VodagoneMaterialModule} from '../../modules/material.module';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {LoggingService} from '../../services/logging/logging.service';
import {AbonneeService} from '../../services/abonee/abonee.service';

describe('AbonnementComponent', () => {
  let component: AbonnementComponent;
  let fixture: ComponentFixture<AbonnementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AbonnementComponent
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
    fixture = TestBed.createComponent(AbonnementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
