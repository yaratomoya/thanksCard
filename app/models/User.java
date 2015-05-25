package models;

import javax.persistence.*;
import play.db.ebean.*;
import java.util.*;

@Entity
public class User extends Model{
	@Id
	public Long userID;
	public String userName;
	public String userPassword;
	public Integer permission;
	@ManyToOne
	public Section section;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="receive")
	public List<ThanksCard> card=new ArrayList<ThanksCard>();
	//public ThanksCard card;
	//@OneToMany
	//public User receive;
	//@OneToMany
	//public User send;

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
