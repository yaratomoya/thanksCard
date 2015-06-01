package models;

import java.util.*;
import java.util.Date;
import javax.persistence.*;
import models.*;
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
	//@OneToMany(cascade=CascadeType.ALL)
	//public List<ThanksCard> thanksCard;

	public static Finder<Long, ThanksCard> find=new Finder<Long, ThanksCard>(
			Long.class, ThanksCard.class
	);

	public static Long create(String helpText, String thanksText, User receive, HelpCategory category, Date helpDate, User send, Date send_Date){
		ThanksCard ThanksCard=new ThanksCard();

		ThanksCard.helpText=helpText;
		ThanksCard.thanksText=thanksText;
		ThanksCard.send=send;
		ThanksCard.deleteRequest=0;
		ThanksCard.sendDay=send_Date;
		ThanksCard.category=category;
		ThanksCard.helpDate=helpDate;
		ThanksCard.receive=receive;
		ThanksCard.save();

		return ThanksCard.cardID;
	}
}
