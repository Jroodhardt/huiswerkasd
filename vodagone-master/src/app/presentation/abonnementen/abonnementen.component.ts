import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Abonnementen} from '../../models/abonnementen/abonnementen.interface.model';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {Abonnement} from '../../models/abonnement/abonnement.interface';
import {AbonnementenImpl} from '../../models/abonnementen/abonnementen.model';
import {Aanbieder} from '../../models/abonnement/enums/aanbieder.enum';
import {SelectAbonnementComponent} from '../select-abonnement/select-abonnement.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-abonnementen',
  templateUrl: './abonnementen.component.html',
  styleUrls: ['./abonnementen.component.scss']
})
export class AbonnementenComponent implements OnInit {

  public abonnementen: Abonnementen;
  public selectedAbonnementId: number;
  public aanbieder = Aanbieder;

  @Output() selectedAbonnementChange = new EventEmitter<number>();

  constructor(private abonnementenService: AbonnementenService,
              public dialog: MatDialog) {
    this.setEmptyAbonnementen();
  }

  ngOnInit() {
    this.updateAbonnementen();
  }


  /**
   * Select a abonnement from the list.
   *
   * @param {Abonnement} abonnement
   */
  public onAbonnementSelected(abonnement: Abonnement): void {
    this.selectedAbonnementChange.emit(abonnement.id);
    this.selectedAbonnementId = abonnement.id;
  }

  /**
   * Request a new Abonnement.
   */
  public onNewAbonnementRequested(): void {
    const aboneesDialogRef = this.dialog.open(SelectAbonnementComponent, {
      disableClose: false,
    });

    aboneesDialogRef.afterClosed().subscribe(abonnement => {
        if (abonnement) {
          console.log('Received a new abonnement: ', abonnement);

          this.abonnementenService.addAbonnement(abonnement).then(abonnementen => this.setAbonnementen(abonnementen))
            .catch(any => this.setEmptyAbonnementen());
        }
      }
    );
  }

  private setAbonnementen(abonn: Abonnementen): void {
    this.abonnementen = abonn;

    if (this.abonnementen.abonnementen.length > 0) {

      let abonnementToSelect = this.abonnementen.abonnementen[0];

      if (this.selectedAbonnementId) {
        for (const abonnement of this.abonnementen.abonnementen) {
          if (abonnement.id === this.selectedAbonnementId) {
            abonnementToSelect = abonnement;
            break;
          }
        }
      }

      this.onAbonnementSelected(abonnementToSelect);
    } else {
      this.onAbonnementSelected(undefined);
    }
  }

  private setEmptyAbonnementen(): void {
    this.abonnementen = new AbonnementenImpl();
  }

  private updateAbonnementen(): void {
    this.abonnementenService.getAbonnementen().then(abonnementen => this.setAbonnementen(abonnementen))
      .catch(any => this.setEmptyAbonnementen());
  }
}
