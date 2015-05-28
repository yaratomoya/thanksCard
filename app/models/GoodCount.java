package models;

import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class GoodCount extends Model{
	@ManyToOne
	public User user;
	@ManyToOne
	public ThanksCard cards;

	public static Finder<Long,GoodCount> find=new Finder<Long,GoodCount>(
			Long.class, GoodCount.class
	);


}
