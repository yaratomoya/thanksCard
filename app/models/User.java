package models;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class User extends Model{
	@Id
	public Long id;
	public String name;
	public String password;
	public Integer permission;

	public static Finder<Long, User> find=new Finder<Long, User>(
		Long.class, User.class
	);

	public static Boolean authenticate(String username, String password){
		User user=find.where().eq("name", username).findUnique();
		return (user != null && user.password.equals(password));
	}

	public static Long create(String username, String password, Integer permission){
		User user=new User();
		user.name=username;
		user.password=password;
		user.permission=permission;
		user.save();
		return user.id;
	}
}
