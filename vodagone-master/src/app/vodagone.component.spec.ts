import {async, TestBed} from '@angular/core/testing';
import {VodagoneComponent} from './vodagone.component';
import {LoginComponent} from './presentation/login/login.component';
import {VodagoneMaterialModule} from './modules/material.module';
import {VodagoneAngularModule} from './modules/angular.module';
import {LoginService} from './services/login/login.service';
import {LoggingService} from './services/logging/logging.service';
import {AbonnementComponent} from './presentation/abonnement/abonnement.component';
import {AbonnementenComponent} from './presentation/abonnementen/abonnementen.component';
import {AbonnementenOverviewComponent} from './presentation/abonnementen-overview/abonnementen-overview.component';
import {AbonnementenService} from './services/abonnementen/abonnementen.service';
import {AbonneeService} from './services/abonee/abonee.service';

describe('VodagoneComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AbonnementComponent,
        AbonnementenComponent,
        AbonnementenOverviewComponent,
        VodagoneComponent,
        LoginComponent
      ],
      imports: [
        VodagoneMaterialModule,
        VodagoneAngularModule
      ],
      providers: [
        AbonnementenService,
        AbonneeService,
        LoginService,
        LoggingService
      ]
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(VodagoneComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
