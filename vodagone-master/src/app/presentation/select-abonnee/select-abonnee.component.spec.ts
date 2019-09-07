/* tslint:disable:no-unused-variable */
import {async, TestBed} from '@angular/core/testing';
import {SelectAbonneeComponent} from './select-abonnee.component';
import {NgModule} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {VodagoneMaterialModule} from '../../modules/material.module';
import {LoggingService} from '../../services/logging/logging.service';
import {AbonneeService} from '../../services/abonee/abonee.service';

@NgModule({
  entryComponents: [
    SelectAbonneeComponent
  ],
})
export class TestModule {
}

describe('SelectAbonneeComponent', () => {
  let component: SelectAbonneeComponent;
  let dialog: MatDialog;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SelectAbonneeComponent
      ],
      imports: [
        VodagoneAngularModule,
        VodagoneMaterialModule,
        TestModule
      ],
      providers: [
        AbonneeService,
        LoggingService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    dialog = TestBed.get(MatDialog);
    const dialogRef = dialog.open(SelectAbonneeComponent);
    component = dialogRef.componentInstance;
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
