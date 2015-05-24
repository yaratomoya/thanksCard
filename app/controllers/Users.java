package controllers;

import java.util.*;
import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.users.*;

public class Users extends Controller{
	public static Form<User> userForm=Form.form(User.class);

	public static Result index(){
		List<User> users=User.find.all();
		return ok(index.render(users));
	}

	public static Result newUser(){
		return ok(newForm.render(userForm));
	}

	public static Result createUser(){
		Form<User> form=userForm.bindFromRequest();

		if(form.hasErrors()){
			return badRequest(newForm.render(form));
		}

		User user=form.get();
		User.create(user.userName, user.userPassword, user.permission);
		return redirect(routes.Users.index());
	}
}
