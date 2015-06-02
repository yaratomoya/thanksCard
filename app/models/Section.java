package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Section extends Model{
	@Id
	public Long sectionID;
	public String sectionName;
	public Integer delete;
	//@OneToMany
	//public List<User> user;

	public static Finder<Long, Section> find=new Finder<Long, Section>(
			Long.class, Section.class
	);

	public static Long create(String name, Integer delete){
		Section section=new Section();
		section.sectionName=name;
		section.delete=0;
		section.save();
		return section.sectionID;
	}
}
