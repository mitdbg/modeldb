package ai.verta.modeldb.common.authservice;

import ai.verta.modeldb.common.CommonConstants;
import ai.verta.modeldb.common.CommonMessages;
import ai.verta.modeldb.common.CommonUtils;
import ai.verta.modeldb.common.CommonUtils.RetryCallInterface;
import ai.verta.modeldb.common.dto.UserInfoPaginationDTO;
import ai.verta.uac.Empty;
import ai.verta.uac.GetUser;
import ai.verta.uac.GetUsers;
import ai.verta.uac.GetUsersFuzzy;
import ai.verta.uac.UserInfo;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthServiceUtils implements AuthService {
  private final String host;
  private final Integer port;
  private final String serviceUserEmail;
  private final String serviceUserDevKey;
  private Integer timeout;

  public AuthServiceUtils(
      String host,
      Integer port,
      String serviceUserEmail,
      String serviceUserDevKey,
      Integer timeout) {
    this.host = host;
    this.port = port;
    this.serviceUserEmail = serviceUserEmail;
    this.serviceUserDevKey = serviceUserDevKey;
    this.timeout = timeout;
  }

  private static final Logger LOGGER = LogManager.getLogger(AuthServiceUtils.class);

  @Override
  public UserInfo getCurrentLoginUserInfo() {
    return getCurrentLoginUserInfo(true);
  }

  private UserInfo getCurrentLoginUserInfo(boolean retry) {
    try (AuthServiceChannel authServiceChannel = getAuthServiceChannel()) {
      LOGGER.info(CommonMessages.AUTH_SERVICE_REQ_SENT_MSG);
      UserInfo userInfo =
          authServiceChannel.getUacServiceBlockingStub().getCurrentUser(Empty.newBuilder().build());
      LOGGER.info(CommonMessages.AUTH_SERVICE_RES_RECEIVED_MSG);

      if (userInfo == null || userInfo.getVertaInfo() == null) {
        LOGGER.info("user not found {}", userInfo);
        Status status =
            Status.newBuilder()
                .setCode(Code.NOT_FOUND_VALUE)
                .setMessage("Current user could not be resolved.")
                .build();
        throw StatusProto.toStatusRuntimeException(status);
      } else {
        return userInfo;
      }
    } catch (StatusRuntimeException ex) {
      return (UserInfo)
          CommonUtils.retryOrThrowException(
              ex, retry, (RetryCallInterface<UserInfo>) this::getCurrentLoginUserInfo, timeout);
    }
  }

  @Override
  public UserInfo getUnsignedUser() {
    return getUnsignedUser(true);
  }

  private UserInfo getUnsignedUser(boolean retry) {
    try (AuthServiceChannel authServiceChannel = getAuthServiceChannel()) {
      LOGGER.info(CommonMessages.AUTH_SERVICE_REQ_SENT_MSG);
      GetUser getUserRequest =
          GetUser.newBuilder().setUsername(CommonConstants.UNSIGNED_USER).build();
      // Get the user info by vertaId form the AuthService
      UserInfo userInfo = authServiceChannel.getUacServiceBlockingStub().getUser(getUserRequest);
      LOGGER.info(CommonMessages.AUTH_SERVICE_RES_RECEIVED_MSG);

      if (userInfo == null || userInfo.getVertaInfo() == null) {
        LOGGER.warn("unsigned user not found {}", userInfo);
        Status status =
            Status.newBuilder()
                .setCode(Code.NOT_FOUND_VALUE)
                .setMessage("Unsigned user not found with the provided metadata")
                .build();
        throw StatusProto.toStatusRuntimeException(status);
      } else {
        return userInfo;
      }
    } catch (StatusRuntimeException ex) {
      return (UserInfo)
          CommonUtils.retryOrThrowException(
              ex, retry, (RetryCallInterface<UserInfo>) this::getUnsignedUser, timeout);
    }
  }

  @Override
  public UserInfo getUserInfo(String vertaId, CommonConstants.UserIdentifier vertaIdentifier) {
    return getUserInfo(true, vertaId, vertaIdentifier);
  }

  private UserInfo getUserInfo(
      boolean retry, String vertaId, CommonConstants.UserIdentifier vertaIdentifier) {
    try (AuthServiceChannel authServiceChannel = getAuthServiceChannel()) {
      GetUser getUserRequest;
      if (vertaIdentifier == CommonConstants.UserIdentifier.EMAIL_ID) {
        getUserRequest = GetUser.newBuilder().setEmail(vertaId).build();
      } else if (vertaIdentifier == CommonConstants.UserIdentifier.USER_NAME) {
        getUserRequest = GetUser.newBuilder().setUsername(vertaId).build();
      } else {
        getUserRequest = GetUser.newBuilder().setUserId(vertaId).build();
      }

      LOGGER.info(CommonMessages.AUTH_SERVICE_REQ_SENT_MSG);
      // Get the user info by vertaId form the AuthService
      UserInfo userInfo = authServiceChannel.getUacServiceBlockingStub().getUser(getUserRequest);
      LOGGER.info(CommonMessages.AUTH_SERVICE_RES_RECEIVED_MSG);

      if (userInfo == null || userInfo.getVertaInfo() == null) {
        LOGGER.info("user not found with id {}", vertaId);
        Status status =
            Status.newBuilder()
                .setCode(Code.NOT_FOUND_VALUE)
                .setMessage("User not found with the provided metadata")
                .build();
        throw StatusProto.toStatusRuntimeException(status);
      } else {
        return userInfo;
      }
    } catch (StatusRuntimeException ex) {
      return (UserInfo)
          CommonUtils.retryOrThrowException(
              ex,
              retry,
              (RetryCallInterface<UserInfo>)
                  (retry1) -> getUserInfo(retry1, vertaId, vertaIdentifier),
              timeout);
    }
  }

  /**
   * @param vertaIdList : vertaId list which is now deprecated
   * @param emailIdList : email id list
   * @param usernameList : username list
   * @return Map from verta_id to userInfo
   */
  @Override
  public Map<String, UserInfo> getUserInfoFromAuthServer(
      Set<String> vertaIdList, Set<String> emailIdList, List<String> usernameList) {
    return getUserInfoFromAuthServer(true, vertaIdList, emailIdList, usernameList);
  }

  private Map<String, UserInfo> getUserInfoFromAuthServer(
      boolean retry, Set<String> vertaIdList, Set<String> emailIdList, List<String> usernameList) {
    try (AuthServiceChannel authServiceChannel = getAuthServiceChannel()) {
      GetUsers.Builder getUserRequestBuilder = GetUsers.newBuilder().addAllUserIds(vertaIdList);
      if (emailIdList != null && !emailIdList.isEmpty()) {
        getUserRequestBuilder.addAllEmails(emailIdList);
      }
      if (usernameList != null && !usernameList.isEmpty()) {
        getUserRequestBuilder.addAllUsernames(usernameList);
      }
      LOGGER.trace("vertaIdList : {}", vertaIdList);
      LOGGER.trace("email Id List : {}", emailIdList);
      LOGGER.trace("username Id List : {}", usernameList);
      // Get the user info from the Context
      GetUsers.Response response =
          authServiceChannel.getUacServiceBlockingStub().getUsers(getUserRequestBuilder.build());
      LOGGER.info(CommonMessages.AUTH_SERVICE_RES_RECEIVED_MSG);
      List<UserInfo> userInfoList = response.getUserInfosList();

      Map<String, UserInfo> useInfoMap = new HashMap<>();
      for (UserInfo userInfo : userInfoList) {
        useInfoMap.put(userInfo.getVertaInfo().getUserId(), userInfo);
      }
      return useInfoMap;
    } catch (StatusRuntimeException ex) {
      return (Map<String, UserInfo>)
          CommonUtils.retryOrThrowException(
              ex,
              retry,
              (RetryCallInterface<Map<String, UserInfo>>)
                  (retry1) ->
                      getUserInfoFromAuthServer(retry1, vertaIdList, emailIdList, usernameList),
              timeout);
    }
  }

  @Override
  public String getVertaIdFromUserInfo(UserInfo userInfo) {
    if (userInfo != null
        && userInfo.getVertaInfo() != null
        && !userInfo.getVertaInfo().getUserId().isEmpty()) {
      return userInfo.getVertaInfo().getUserId();
    }
    Status status =
        Status.newBuilder()
            .setCode(Code.NOT_FOUND_VALUE)
            .setMessage("VertaId not found in userInfo")
            .build();
    throw StatusProto.toStatusRuntimeException(status);
  }

  @Override
  public String getUsernameFromUserInfo(UserInfo userInfo) {
    if (userInfo != null
        && userInfo.getVertaInfo() != null
        && !userInfo.getVertaInfo().getUsername().isEmpty()) {
      return userInfo.getVertaInfo().getUsername();
    }
    Status status =
        Status.newBuilder()
            .setCode(Code.NOT_FOUND_VALUE)
            .setMessage("Username not found in userInfo")
            .build();
    throw StatusProto.toStatusRuntimeException(status);
  }

  @Override
  public boolean isCurrentUser(String vertaID) {
    return getVertaIdFromUserInfo(getCurrentLoginUserInfo()).equals(vertaID);
  }

  @Override
  public UserInfoPaginationDTO getFuzzyUserInfoList(String usernameChar) {
    return getFuzzyUserInfoList(true, usernameChar);
  }

  private UserInfoPaginationDTO getFuzzyUserInfoList(boolean retry, String usernameChar) {
    if (usernameChar.isEmpty()) {
      UserInfoPaginationDTO paginationDTO = new UserInfoPaginationDTO();
      paginationDTO.setUserInfoList(Collections.emptyList());
      paginationDTO.setTotalRecords(0L);
      return paginationDTO;
    }
    try (AuthServiceChannel authServiceChannel = getAuthServiceChannel()) {
      GetUsersFuzzy.Builder getUserRequestBuilder =
          GetUsersFuzzy.newBuilder().setUsername(usernameChar);

      LOGGER.trace("usernameChar : {}", usernameChar);
      // Get the user info from the Context
      GetUsersFuzzy.Response response =
          authServiceChannel
              .getUacServiceBlockingStub()
              .getUsersFuzzy(getUserRequestBuilder.build());
      LOGGER.info(CommonMessages.AUTH_SERVICE_RES_RECEIVED_MSG);

      UserInfoPaginationDTO paginationDTO = new UserInfoPaginationDTO();
      paginationDTO.setUserInfoList(response.getUserInfosList());
      paginationDTO.setTotalRecords(response.getTotalRecords());
      return paginationDTO;
    } catch (StatusRuntimeException ex) {
      return (UserInfoPaginationDTO)
          CommonUtils.retryOrThrowException(
              ex,
              retry,
              (RetryCallInterface<UserInfoPaginationDTO>)
                  (retry1) -> getFuzzyUserInfoList(retry1, usernameChar),
              timeout);
    }
  }

  private AuthServiceChannel getAuthServiceChannel() {
    return new AuthServiceChannel(host, port, serviceUserEmail, serviceUserDevKey);
  }
}
