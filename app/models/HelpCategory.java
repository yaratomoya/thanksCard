package models;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import javax.persistence.*;
import java.util.*;

@Entity
public class HelpCategory extends Model{
	@Id
	public Long categoryID;
	public String categoryName;
	public Integer delete;
	//@OneToMany
	//public List<ThanksCard> cards;

	public static Finder<Long, HelpCategory> find=new Finder<Long, HelpCategory>(
			Long.class, HelpCategory.class
		);

	public static Long create(String name,Integer delete){
		HelpCategory category=new HelpCategory();
		category.categoryName=name;
		category.delete=0;
		category.save();
		return category.categoryID;
	}
}
