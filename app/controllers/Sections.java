package controllers;

import java.util.HashMap;
import java.util.List;

import models.Section;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.section.index;
import views.html.section.newForm;

public class Sections extends Controller{
	public static Form<Section> sectionForm=Form.form(Section.class);

	public static Result index(){
		List<Section> section=Section.find.where().eq("delete","0").findList();
		return ok(index.render(section));
	}

	public static Result newSection(){
    	HashMap<String, String> Sections=new HashMap<>();
        for(Section section : Section.find.where().eq("delete","0").findList()){
            Sections.put(section.sectionID.toString(), section.sectionName);
        }

		return ok(newForm.render(sectionForm,Sections));
	}

	public static Result createSection(){
		Form<Section> form=sectionForm.bindFromRequest();

//		if(form.hasErrors()){
//			return badRequest(newForm.render(form));
//		}

		Section section=form.get();
		Section.create(section.sectionName, section.delete);
		return redirect(routes.Sections.index());
	}

	public static Result updateSection(){
		Form<Section> form=sectionForm.bindFromRequest();

		Section section=form.get();
		Section sectionUp=Section.find.where().eq("sectionID", section.sectionID).findUnique();
		sectionUp.sectionID=section.sectionID;
		sectionUp.sectionName=section.sectionName;
		sectionUp.update(sectionUp.sectionID);

		return redirect(routes.Sections.index());
	}


	public static Result deleteSection(){
		Form<Section> form=sectionForm.bindFromRequest();
		Section section=form.get();
		Section sectionUp=Section.find.where().eq("sectionID", section.sectionID).findUnique();

		sectionUp.delete=1;
		sectionUp.update();

		return redirect(routes.Sections.index());
	}


}