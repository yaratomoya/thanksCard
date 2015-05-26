package models;

import javax.persistence.*;
import play.db.ebean.Model;

@Entity
public class GoodCount extends Model{
	@Id
	public Long userid;
	@Id
	public Long cardid;
}
