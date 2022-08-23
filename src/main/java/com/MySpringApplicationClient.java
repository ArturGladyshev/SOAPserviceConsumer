package com;

import com.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringApplicationClient {
		public static void main(String[] args) {
				SpringApplication.run(MySpringApplicationClient.class, args);
		}
		@Bean
		CommandLineRunner lookup(UserClient userClient) {
				return args -> {

						System.out.println("--- Add User ---");
						UserRolesInfo userRolesInfo = new UserRolesInfo();
						userRolesInfo.setName("Sergei");
						userRolesInfo.setLogin("Sergio");
						userRolesInfo.setPassword("Ser0");
						RoleInfo roleInfo = new RoleInfo();
						roleInfo.setName("Admin");
						userRolesInfo.getRoles().add(roleInfo);
						roleInfo = new RoleInfo();
						roleInfo.setName("Operator");
						userRolesInfo.getRoles().add(roleInfo);
						AddUserResponse addUserResponse = userClient.addUser(userRolesInfo);
						userRolesInfo = addUserResponse.getUserRolesInfo();
						this.printUserRolesInfo(userRolesInfo);
						ServiceStatus serviceStatus = addUserResponse.getServiceStatus();
						System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
							", Message: " + serviceStatus.getMessage());

						System.out.println("--- Get User by Login ---");
						GetUserByLoginResponse userByLoginResponse = userClient.getUserByLogin("Sergio");
						userRolesInfo = userByLoginResponse.getUserRolesInfo();
						this.printUserRolesInfo(userRolesInfo);

						System.out.println("--- Get All Users ---");
						GetAllUsersResponse allUsersResponse = userClient.getAllUsers();
						allUsersResponse.getUserInfo().stream()
							.forEach(userInfo -> System.out.println(userInfo.getLogin() + ", "+ userInfo.getName() + ", " + userInfo.getPassword()));

						System.out.println("--- Update User ---");
						userRolesInfo = new UserRolesInfo();
						userRolesInfo.setLogin("Sergio");
						userRolesInfo.setName("Alex");
						userRolesInfo.setPassword("Alex01");
						roleInfo = new RoleInfo();
						roleInfo.setName("Moderator");
						userRolesInfo.getRoles().add(roleInfo);
						UpdateUserResponse updateUserResponse = userClient.updateUser(userRolesInfo);
						serviceStatus = updateUserResponse.getServiceStatus();
						System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
							", Message: " + serviceStatus.getMessage());
						this.printUserRolesInfo(userRolesInfo);

						System.out.println("--- Delete User ---");
						DeleteUserResponse deleteUserResponse = userClient.deleteUser("Sergio");
						serviceStatus = deleteUserResponse.getServiceStatus();
						System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
							", Message: " + serviceStatus.getMessage());
				};
		}
private void printUserRolesInfo (UserRolesInfo userRolesInfo)
{
		if (userRolesInfo != null)
		{
				System.out.print(userRolesInfo.getLogin() + ", " + userRolesInfo.getName() + ", " + userRolesInfo.getPassword()
					+ ", [ ");
				userRolesInfo.getRoles().stream().forEach(role -> System.out.print(role.getName() + " "));
				System.out.print("]\n");
		}
		}
}