package models;

import javax.persistence.*;
import play.db.ebean.*;
import java.util.*;

@Entity
public class User extends Model{
	@Id
	public Long userID;
	public String userCD;
	public String userName;
	public String userPassword;
	public Integer permission;
	@ManyToOne
	public Section section;

	public static Finder<Long, User> find=new Finder<Long, User>(
		Long.class, User.class
	);

	public static Boolean authenticate(String userCD, String userPassword){
		User user=find.where().eq("userCD", userCD).findUnique();
		return (user != null && user.userPassword.equals(userPassword));
	}

	public static Long create(String username, String password, Integer permission){
		User user=new User();
		user.userName=username;
		user.userPassword=password;
		user.permission=permission;
		user.save();
		return user.userID;
	}
}
