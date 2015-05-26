package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class ThanksCard extends Model{
	@Id
	public Long cardID;
	public String helpText;
	public String thanksText;
	public Date sendDay;
	public Integer deleteRequest;
	public Date helpDate;
	@ManyToOne
	public HelpCategory category;
	@ManyToOne
	public User receive;
	@ManyToOne
	public User send;

	public static Finder<Long, ThanksCard> find=new Finder<Long, ThanksCard>(
			Long.class, ThanksCard.class
	);
}
