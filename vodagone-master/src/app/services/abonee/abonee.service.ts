import {Injectable} from '@angular/core';
import {RestfulClientService} from '../restful-client.service';
import {HttpClient} from '@angular/common/http';
import {LoggingService} from '../logging/logging.service';
import {Abonnee} from '../../models/abonnee/abonnee.interface';
import {VodagoneConstants} from '../../vodagone.constants';
import {Abonnement} from '../../models/abonnement/abonnement.interface';

@Injectable()
export class AbonneeService extends RestfulClientService {

  /**
   * Create a new AbonneeService
   *
   * @param {HttpClient} httpClient
   * @param {LoggingService} loggingService
   */
  constructor(private httpClient: HttpClient,
              loggingService: LoggingService) {

    super(loggingService);
  }

  /**
   * Return a complete list of Abonnees.
   *
   * @return {Promise<Abonnee[]>} The complete list of Abonnees.
   */
  public async getAbonnementen(): Promise<Abonnee[]> {
    const endpointUrl = this.getAbonneeEndpoint(undefined);
    const params = this.createtokenParam();

    try {
      const data: Abonnee[] = await this.httpClient.get<Abonnee[]>(endpointUrl, {params: params}).toPromise();
      return data;
    } catch (err) {
      this.handleErrors(err);
      return Promise.reject(err);
    }
  }

  /**
   * Share an Abonnement with an Abonnee.
   */
  public shareAbonnement(abonnee: Abonnee, abonnement: Abonnement): Promise<any> {
    const endpointUrl = this.getAbonneeEndpoint(abonnee.id);
    const params = this.createtokenParam();


    return this.httpClient.post<void>(endpointUrl,
      JSON.stringify({'id': abonnement.id}),
      {
        headers: this.headers,
        params: params
      }
    ).toPromise();
  }

  private getAbonneeEndpoint(id: number): string {
    const baseEndpointUrl = this.createEndpointUrl(VodagoneConstants.API_ABONNEES);

    if (id || id === 0) {
      return (baseEndpointUrl.concat('/')).concat(id.toString());
    } else {
      return baseEndpointUrl;
    }
  }
}
