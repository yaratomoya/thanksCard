package models;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import javax.persistence.*;

@Entity
public class HelpCategory extends Model{
	@Id
	@ManyToOne
	public Long categoryID;
	public String categoryName;
	@ManyToOne
	public ThanksCard card;

	public static Finder<Long, HelpCategory> find=new Finder<Long, HelpCategory>(
			Long.class, HelpCategory.class
		);

	public static Long create(String name){
		HelpCategory category=new HelpCategory();
		category.categoryName=name;
		category.save();
		return category.categoryID;
	}
}
