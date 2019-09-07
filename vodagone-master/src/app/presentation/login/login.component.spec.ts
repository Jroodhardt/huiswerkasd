import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LoginComponent} from './login.component';
import {LoginService} from '../../services/login/login.service';
import {VodagoneMaterialModule} from '../../modules/material.module';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {LoggingService} from '../../services/logging/logging.service';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let loginService: LoginService;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LoginComponent
      ],
      imports: [
        VodagoneMaterialModule,
        VodagoneAngularModule
      ],
      providers: [
        LoginService,
        LoggingService
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    loginService = fixture.debugElement.injector.get(LoginService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should delegate login() to the service', () => {
    const spy = spyOn(loginService, 'login');

    component.login();
    expect(spy).toHaveBeenCalled();
  });
});
