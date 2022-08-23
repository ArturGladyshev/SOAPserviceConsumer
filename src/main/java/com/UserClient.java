package com;

import com.wsdl.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UserClient extends WebServiceGatewaySupport
{
		public GetUserByLoginResponse getUserByLogin(String login)
		{
				GetUserByLoginRequest request = new GetUserByLoginRequest();
				request.setLogin(login);
				GetUserByLoginResponse response = (GetUserByLoginResponse)getWebServiceTemplate().marshalSendAndReceive(
					request, new SoapActionCallback("http://localhost:8080/soapws/getUserByLoginRequest"));
				return response;
		}

		public GetAllUsersResponse getAllUsers()
		{
				GetAllUsersRequest request = new GetAllUsersRequest();
				GetAllUsersResponse response = (GetAllUsersResponse)getWebServiceTemplate().marshalSendAndReceive(
					request, new SoapActionCallback("http://localhost:8080/soapws/getAllUsersRequest"));
				return response;
		}

		public AddUserResponse addUser(UserRolesInfo userRolesInfo)
		{
				AddUserRequest request = new AddUserRequest();
				request.setUserRolesInfo(userRolesInfo);
				AddUserResponse response = (AddUserResponse)getWebServiceTemplate().marshalSendAndReceive(
					request, new SoapActionCallback("http://localhost:8080/soapws/addUserRequest"));
				return response;
		}

		public UpdateUserResponse updateUser(UserRolesInfo userRolesInfoInfo)
		{
				UpdateUserRequest request = new UpdateUserRequest();
				request.setUserRolesInfo(userRolesInfoInfo);
				UpdateUserResponse response = (UpdateUserResponse)getWebServiceTemplate().marshalSendAndReceive(
					request, new SoapActionCallback("http://localhost:8080/soapws/updateUserRequest"));
				return response;
		}

		public DeleteUserResponse deleteUser(String login)
		{
				DeleteUserRequest request = new DeleteUserRequest();
				request.setLogin(login);
				DeleteUserResponse response = (DeleteUserResponse)getWebServiceTemplate().marshalSendAndReceive(
					request, new SoapActionCallback("http://localhost:8080/soapws/deleteUserRequest"));
				return response;
		}
}
