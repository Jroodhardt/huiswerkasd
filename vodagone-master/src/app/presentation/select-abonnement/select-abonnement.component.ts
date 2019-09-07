import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {Abonnement} from '../../models/abonnement/abonnement.interface';
import {Aanbieder} from '../../models/abonnement/enums/aanbieder.enum';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/fromEvent';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'app-select-abonnement',
  templateUrl: './select-abonnement.component.html',
  styleUrls: ['./select-abonnement.component.scss']
})
export class SelectAbonnementComponent implements OnInit {

  public abonnementen: Abonnement[];
  public selectedAbonnement: Abonnement;
  public aanbieder = Aanbieder;

  @ViewChild('filter') filter: ElementRef;

  constructor(private abonnementenService: AbonnementenService,
              private dialogRef: MatDialogRef<SelectAbonnementComponent>) {
  }

  ngOnInit(): void {
    this.abonnementenService.getAllAbonnementen('').then(abonnementen => this.setAbonnementen(abonnementen));

    Observable.fromEvent(this.filter.nativeElement, 'keyup')
      .debounceTime(300)
      .distinctUntilChanged()
      .subscribe(() => {
        const filterValue = this.filter.nativeElement.value;
        this.abonnementenService.getAllAbonnementen(filterValue).then(abonnementen => this.setAbonnementen(abonnementen));
      });
  }

  public onOk(): void {
    this.dialogRef.close(this.selectedAbonnement);
  }

  public onAbonnementSelected(selectedAbonnement: Abonnement): void {
    this.selectedAbonnement = selectedAbonnement;
  }

  private setAbonnementen(abonnementen: Abonnement[]): void {

    this.abonnementen = abonnementen;
    this.selectedAbonnement = undefined;
  }
}
