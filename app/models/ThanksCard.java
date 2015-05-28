package models;

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

	public static Finder<Long, ThanksCard> find=new Finder<Long, ThanksCard>(
			Long.class, ThanksCard.class
	);

	public static Long create(String helpText, String thanksText, Long receive, Long category, Date helpDate, Long send, Date send_Date){
		ThanksCard ThanksCard=new ThanksCard();

		ThanksCard.helpText=helpText;
		ThanksCard.thanksText=thanksText;
		ThanksCard.send=(User.find.byId(send));
		ThanksCard.deleteRequest = 0;
		ThanksCard.sendDay = send_Date;
		ThanksCard.category = HelpCategory.find.byId(category);
		ThanksCard.helpDate=helpDate;
		ThanksCard.receive = (User.find.byId(receive));
		ThanksCard.save();

		return ThanksCard.cardID;
	}
}
