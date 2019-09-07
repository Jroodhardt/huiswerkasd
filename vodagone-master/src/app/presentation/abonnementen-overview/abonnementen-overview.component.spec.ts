import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AbonnementenOverviewComponent} from './abonnementen-overview.component';
import {AbonnementComponent} from '../abonnement/abonnement.component';
import {AbonnementenComponent} from '../abonnementen/abonnementen.component';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {VodagoneMaterialModule} from '../../modules/material.module';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {LoggingService} from '../../services/logging/logging.service';
import {AbonneeService} from '../../services/abonee/abonee.service';

describe('AbonnementenOverviewComponent', () => {
  let component: AbonnementenOverviewComponent;
  let fixture: ComponentFixture<AbonnementenOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AbonnementenOverviewComponent,
        AbonnementComponent,
        AbonnementenComponent],
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
    fixture = TestBed.createComponent(AbonnementenOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
