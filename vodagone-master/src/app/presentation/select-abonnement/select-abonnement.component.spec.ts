import {async, TestBed} from '@angular/core/testing';
import {NgModule} from '@angular/core';
import {SelectAbonnementComponent} from './select-abonnement.component';
import {MatDialog} from '@angular/material/dialog';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {LoggingService} from '../../services/logging/logging.service';
import {VodagoneAngularModule} from '../../modules/angular.module';
import {VodagoneMaterialModule} from '../../modules/material.module';


@NgModule({
  entryComponents: [
    SelectAbonnementComponent
  ],
})
export class TestModule {
}

describe('SelectAbonnementComponent', () => {
  let component: SelectAbonnementComponent;
  let dialog: MatDialog;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        SelectAbonnementComponent
      ],
      imports: [
        VodagoneAngularModule,
        VodagoneMaterialModule,
        TestModule
      ],
      providers: [
        AbonnementenService,
        LoggingService]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    dialog = TestBed.get(MatDialog);
    const dialogRef = dialog.open(SelectAbonnementComponent);
    component = dialogRef.componentInstance;
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
