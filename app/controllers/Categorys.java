package controllers;

import java.util.*;
import models.HelpCategory;
import play.data.Form;
import play.mvc.*;
import views.html.categorys.*;

public class Categorys extends Controller{
	public static Form<HelpCategory> categoryForm=Form.form(HelpCategory.class);

	public static Result index(){
		List<HelpCategory> category = HelpCategory.find.all();
		return ok(index.render(category));
	}

	public static Result newCategory(){
		return ok(newForm.render(categoryForm));
	}

	public static Result createCategory(){
		Form<HelpCategory> form=categoryForm.bindFromRequest();

		if(form.hasErrors()){
			return badRequest(newForm.render(form));
		}

		HelpCategory category=form.get();
		HelpCategory.create(category.categoryName);
		return redirect(routes.Categorys.index());
	}


	public static Result updateCategory(){
		return ok("update");
	}


	public static Result deleteCategory(){
		return ok("delete");
	}
}
