import {Component} from '@angular/core';
import {Abonnement} from '../../models/abonnement/abonnement.interface';
import {AbonnementImpl} from '../../models/abonnement/abonnement.model';
import {Aanbieder} from '../../models/abonnement/enums/aanbieder.enum';
import {AbonnementStatus} from '../../models/abonnement/enums/abonnement.status.enum';
import {AbonnementenService} from '../../services/abonnementen/abonnementen.service';
import {MatDialog} from '@angular/material';
import {SelectAbonneeComponent} from '../select-abonnee/select-abonnee.component';
import {AbonneeService} from '../../services/abonee/abonee.service';
import {AbonnementUpgradabillity} from '../../models/abonnement/enums/abonnement.upgrade.enum';

@Component({
  selector: 'app-abonnement',
  templateUrl: './abonnement.component.html',
  styleUrls: ['./abonnement.component.scss']
})
export class AbonnementComponent {

  public abonnement: Abonnement;

  public abonnementStatus = AbonnementStatus;
  public abonnementVerdubbeling = AbonnementUpgradabillity;


  constructor(private abonneeService: AbonneeService,
              private abonnementenService: AbonnementenService,
              public dialog: MatDialog) {
    this.setEmptyAbonnement();
  }

  /**
   * Upgade this Abonnement.
   */
  public onUpgrade(): void {
    this.abonnementenService.upgadeAbonnement(this.abonnement.id).then(data => this.setAbonnement(data));

  }

  /**
   * Terminate this Abonnement.
   */
  public onTerminate(): void {
    this.abonnementenService.terminateAbonnement(this.abonnement.id).then(data => this.setAbonnement(data));
  }

  public onShare(): void {

    const aboneesDialogRef = this.dialog.open(SelectAbonneeComponent, {
      disableClose: false,
    });

    aboneesDialogRef.afterClosed().subscribe(abonnee => {
        if (abonnee) {
          this.abonneeService.shareAbonnement(abonnee, this.abonnement);
        }
      }
    );
  }

  public onLoadAbonnement(id: number): void {
    this.abonnementenService.getAbonnement(id).then(data => this.setAbonnement(data));
  }

  private setAbonnement(abonnement: Abonnement): void {
    this.abonnement = abonnement;
  }

  private setEmptyAbonnement(): void {
    this.abonnement = new AbonnementImpl(Aanbieder.VODAFONE, '');
  }
}
