package models;

import java.util.Date;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class ThanksCard extends Model{
	@Id
	public Integer cardID;
	public String helpText;
	public String thanksText;
	public Date sendDay;
	public Integer deleteRequest;
	public Date helpDate;
	@ManyToOne
	public HelpCategory category;
	//@ManyToOne
	//public User send;
	@ManyToOne
	public User receive;
	public String sendName;
	public String sendSection;
}
