export class VodagoneConstants {

  private static LOCAL_STORAGE_BASE_KEY = 'vodagone';

  public static LOCAL_STORAGE_KEY_SETTINGS = VodagoneConstants.LOCAL_STORAGE_BASE_KEY + '.settings';

  public static STORAGE_EVENT_LISTENER_KEY = 'storage';

  public static API_LOGIN = '/login';
  public static API_ABONNEMENTEN = '/abonnementen';
  public static API_ABONNEMENTEN_ALL = VodagoneConstants.API_ABONNEMENTEN + '/all';
  public static API_ABONNEES = '/abonnees';
}
