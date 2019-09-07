import {Component, OnInit} from '@angular/core';
import {Settings} from './models/settings/settings.interface.model';
import {LoginService} from './services/login/login.service';
import {MatSnackBar} from '@angular/material';
import {AbonnementenService} from './services/abonnementen/abonnementen.service';
import {AbonneeService} from './services/abonee/abonee.service';

@Component({
  selector: 'app-root',
  templateUrl: './vodagone.component.html',
  styleUrls: ['./vodagone.component.css']
})
export class VodagoneComponent implements OnInit {
  public serverUrl: string;
  public user: string;

  constructor(private loginService: LoginService,
              private abonnementenService: AbonnementenService,
              private abonneeService: AbonneeService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.initSettings();
    this.initErrorSnackbar();
  }

  /**
   * Logout of the application.
   */
  public logout(): void {
    this.loginService.logout();
  }

  private initErrorSnackbar(): void {
    this.loginService.restError$.subscribe(error => this.showError(error));
    this.abonneeService.restError$.subscribe(error => this.showError(error));
    this.abonnementenService.restError$.subscribe(error => this.showError(error));
  }

  private initSettings(): void {
    this.loginService.getSettings()
      .then(settings => this.setSettings(settings))
      .catch(any => this.setSettings(undefined));
    this.loginService.settingsChanged$.subscribe(settings => this.setSettings(settings));
  }

  private setSettings(settings: Settings): void {
    if (settings) {
      this.serverUrl = settings.server;
      this.user = settings.user;
    } else {
      this.serverUrl = undefined;
      this.user = undefined;
    }
  }

  private showError(error: number): void {
    this.snackBar.open('Http status code ' + error, 'sluit');
  }
}
