package models;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class User extends Model{
	@Id
	public Long userID;
	public String userName;
	public String userPassword;
	public Integer permission;
	@OneToOne(cascade=CascadeType.ALL)
	public Section section;

	public static Finder<Long, User> find=new Finder<Long, User>(
		Long.class, User.class
	);

	public static Boolean authenticate(String username, String password){
		User user=find.where().eq("name", username).findUnique();
		return (user != null && user.userPassword.equals(password));
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
