package de.htwsaar.dfs.service;

import java.util.List;

import de.htwsaar.dfs.can_network.Bootstrap;
import de.htwsaar.dfs.model.User;

/**
 * 
 * @author Aude Nana
 *
 */
public class UserService {

	private Bootstrap bootstrap = new Bootstrap();
	
	public UserService(){
		//just put 2 users in the bootstrap for testing authentication 
		//It will not be able  to add users from the ios app
		bootstrap.createUser("User", "Password");
		bootstrap.createUser("user", "user");
	}

	/**
	 * This Method return all the users 
	 * that are in the bootstrap
	 * @return
	 */
	public List<User> getAllUsers(){
		return bootstrap.getUserList();
		
	}
	
	/**
	 * This Method return a special user in the bootstrap
	 * @param userId
	 * @return
	 */
	public User getUser( int userId) {
		return bootstrap.getUser(userId);
	}
	
	/**
	 * This method update the data of a special user
	 * @param user
	 * @return
	 */
	public User updateUser(User user) {
		if ( user.getId() <= 0 ) {
			return null;
		}
		bootstrap.createUser(user.getName(), user.getPassword());
		return user;
	}

}
