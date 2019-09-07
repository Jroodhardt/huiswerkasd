import {Aanbieder} from './enums/aanbieder.enum';
import {AbonnementStatus} from './enums/abonnement.status.enum';
import {AbonnementUpgradabillity} from './enums/abonnement.upgrade.enum';

export interface Abonnement {
  id: number;
  aanbieder: Aanbieder;
  dienst: string;
  prijs: string;
  startDatum: string;
  verdubbeling: AbonnementUpgradabillity;
  deelbaar: boolean;
  status: AbonnementStatus;
}
