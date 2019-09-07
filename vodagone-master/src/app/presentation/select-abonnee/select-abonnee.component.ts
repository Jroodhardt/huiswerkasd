import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {Abonnee} from '../../models/abonnee/abonnee.interface';
import {AbonneeService} from '../../services/abonee/abonee.service';

@Component({
  selector: 'app-abonnees',
  templateUrl: './select-abonnee.component.html',
  styleUrls: ['./select-abonnee.component.scss']
})
export class SelectAbonneeComponent implements OnInit {

  public abonnees: Abonnee[];
  public selectedAbonnee: Abonnee;

  constructor(private abonneeService: AbonneeService,
              private dialogRef: MatDialogRef<SelectAbonneeComponent>) {
  }

  ngOnInit(): void {
    this.abonneeService.getAbonnementen().then(abonnees => this.setAbonees(abonnees));
  }

  public onOk(): void {
    this.dialogRef.close(this.selectedAbonnee);
  }

  public onAbonneeSelected(selectedAbonnee: Abonnee): void {
    this.selectedAbonnee = selectedAbonnee;
  }

  private setAbonees(abonnees: Abonnee[]): void {
    this.abonnees = abonnees;
  }
}

