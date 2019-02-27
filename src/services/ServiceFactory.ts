import Auth0AuthenticationService from './auth/Auth0AuthenticationService';
import { IAuthenticationService } from './auth/IAuthenticationService';
import ExperimentRunsDataService from './ExperimentRunsDataService';
import { IExperimentRunsDataService, IProjectDataService } from './IApiDataService';
import ProjectDataService from './ProjectDataService';

import CollaboratorsService from './CollaboratorsService';
import MockSFService from './filter/MockSFService';
import { ICollaboratorsService } from './ICollaboratorsService';
import ISearchAndFilterService from './ISearchAndFilterService';

export default class ServiceFactory {
  public static getProjectsService(): IProjectDataService {
    return new ProjectDataService();
  }

  public static getExperimentRunsService(): IExperimentRunsDataService {
    return new ExperimentRunsDataService();
  }

  public static getAuthenticationService(): IAuthenticationService {
    return new Auth0AuthenticationService();
  }
  public static getSearchAndFiltersService(): ISearchAndFilterService | null {
    return new MockSFService();
  }

  public static getCollaboratorsService(): ICollaboratorsService {
    return new CollaboratorsService();
  }
}
