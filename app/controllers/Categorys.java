package controllers;

import java.util.*;

import models.HelpCategory;
import models.Section;
import play.data.Form;
import play.mvc.*;
import views.html.categorys.*;

public class Categorys extends Controller{
	public static Form<HelpCategory> categoryForm=Form.form(HelpCategory.class);

	public static Result index(){
		List<HelpCategory> category = HelpCategory.find.where().eq("delete","0").findList();
		return ok(index.render(category));
	}

	public static Result newCategory(){
    	HashMap<String, String> cate=new HashMap<>();
    	for(HelpCategory category : HelpCategory.find.all()){
    		cate.put(category.categoryID.toString(), category.categoryName);
    	}
		return ok(newForm.render(categoryForm,cate));
	}

	public static Result createCategory(){
		Form<HelpCategory> form=categoryForm.bindFromRequest();

//		if(form.hasErrors()){
//			return badRequest(newForm.render(form));
//		}

		HelpCategory category=form.get();
		HelpCategory.create(category.categoryName, category.delete);
		return redirect(routes.Categorys.index());
	}


	public static Result updateCategory(){
		Form<HelpCategory> form=categoryForm.bindFromRequest();

		HelpCategory category=form.get();
		HelpCategory categoryUp=HelpCategory.find.where().eq("categoryID", category.categoryID).findUnique();
		categoryUp.categoryID=category.categoryID;
		categoryUp.categoryName=category.categoryName;
		categoryUp.update(categoryUp.categoryID);

		return redirect(routes.Categorys.index());
	}


	public static Result deleteCategory(){
		Form<HelpCategory> form=categoryForm.bindFromRequest();

		HelpCategory category=form.get();
		HelpCategory categoryUp=HelpCategory.find.where().eq("categoryID", category.categoryID).findUnique();
		categoryUp.delete=1;
		categoryUp.update();
		return redirect(routes.Categorys.index());
	}
}
