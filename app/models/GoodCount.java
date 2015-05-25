package models;

import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class GoodCount extends Model{
	@Id
	public User user;
	@Id
	public ThanksCard card;
}
