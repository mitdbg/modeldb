import { bind } from 'decko';

import { BaseDataService } from 'core/services/BaseDataService';
import User from 'models/User';
import { convertServerUser } from 'core/services/serverModel/User/converters';

export default class UsersService extends BaseDataService {
  constructor() {
    super();
  }

  @bind
  public async loadUser(userId: string): Promise<User> {
    const response = await this.get<any>({
      url: '/v1/uac-proxy/uac/getUser',
      config: {
        params: {
          user_id: userId,
        },
      },
    });

    return convertServerUser(response.data);
  }

  @bind
  public async loadUsers(userIds: string[]): Promise<User[]> {
    if (userIds.length === 0) {
      return [];
    }
    const response = await this.post<any>({
      url: '/v1/uac-proxy/uac/getUsers',
      data: {
        user_ids: userIds,
      },
    });

    return (response.data.user_infos || []).map(convertServerUser);
  }
}
