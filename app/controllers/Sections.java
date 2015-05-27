package controllers;

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
		List<Section> section=Section.find.all();
		return ok(index.render(section));
	}

	public static Result newSection(){
		return ok(newForm.render(sectionForm));
	}

	public static Result createSection(){
		Form<Section> form=sectionForm.bindFromRequest();

		if(form.hasErrors()){
			return badRequest(newForm.render(form));
		}

		Section section=form.get();
		Section.create(section.sectionName);
		return redirect(routes.Sections.index());
	}

	public static Result updateSection(){
		return ok("update");
	}


	public static Result deleteSection(){
		return ok("delete");
	}


}