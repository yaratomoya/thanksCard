package controllers;

import java.util.*;

import models.HelpCategory;
import models.Section;
import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.users.*;

public class Users extends Controller{
	public static Form<User> userForm=Form.form(User.class);

	public static Result index(){
		List<User> users=User.find.where().eq("delete","0").findList();
		return ok(index.render(users));
	}

	public static Result newUser(){
    	HashMap<String, String> Sections=new HashMap<>();
        for(Section section : Section.find.where().eq("delete","0").findList()){
            Sections.put(section.sectionID.toString(), section.sectionName);
        }

		return ok(newForm.render(userForm,Sections));
	}

	public static Result createUser(){
		Form<User> form=userForm.bindFromRequest();

//		if(form.hasErrors()){
//			return badRequest(newForm.render(form));
//		}

		User user=form.get();
		user.permission=0;
		User.create(user.userCD,user.userName, user.section,user.userPassword, user.permission,user.delete);
		return redirect(routes.Users.index());
	}


	public static Result updateUser(){
		Form<User> form=userForm.bindFromRequest();

		User user=form.get();
		User userUp=User.find.where().eq("userCD", user.userCD).findUnique();
		userUp.userName=user.userName;
		userUp.section=user.section;
		userUp.update(userUp.userID);

		return redirect(routes.Users.index());
	}


	public static Result deleteUser(){
		Form<User> form=userForm.bindFromRequest();

		User user=form.get();
		User userUp=User.find.where().eq("userCD", user.userCD).findUnique();

		userUp.delete=1;
		userUp.update();
		return redirect(routes.Users.index());
	}
}
