package models;

import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class GoodCount extends Model{
	@ManyToOne
	public User user;
	@ManyToOne
	public ThanksCard cards;
}
