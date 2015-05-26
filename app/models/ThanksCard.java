package models;

import java.util.Date;
import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class ThanksCard extends Model{
	@Id
	public Long cardID;
	public String helpText;
	public String thanksText;
	public Date sendDay;
	public Integer deleteRequest;
	public Date helpDate;
	public Long category_id;
	public Long send_id;
	public Long receive_id;

}
