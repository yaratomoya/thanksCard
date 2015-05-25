package models;

import javax.persistence.*;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Section extends Model{
	@Id
	public Long sectionID;
	public String sectionName;
	@OneToMany
	public User user;

	public static Finder<Long, Section> find=new Finder<Long, Section>(
			Long.class, Section.class
	);

	public static Long create(String name){
		Section section=new Section();
		section.sectionName=name;
		section.save();
		return section.sectionID;
	}
}
