package controllers;

import models.User;
import controllers.Login.AuthLogin;
import play.data.Form;
import play.*;
import play.mvc.*;
import views.html.login.*;

public class Login extends Controller {
	public static class AuthLogin{
		public String userCD;
		public String userName;
		public String userPassword;

		public String validate(){
			if(authenticate(userCD, userPassword)){
				return null;
			}
			return "ユーザIDかパスワードが間違っています。";
		}

		private Boolean authenticate(String userCD, String userPassword){
			return User.authenticate(userCD, userPassword);
		}
	}

	public static Form<Login.AuthLogin> loginForm=Form.form(Login.AuthLogin.class);

	public static Result index(){
		if(session("login") != null){
			return ok("あなたは既に" + session("login") + "としてログインしています。");
		}
		return ok(index.render(loginForm));
	}

	public static Result authenticate(){
		Form<AuthLogin> form=loginForm.bindFromRequest();

		if(form.hasErrors()){
			return badRequest(index.render(form));
		}else{
			AuthLogin login=form.get();
			session("login", login.userCD);
			return ok("ようこそ" + login.userName + "さん!!");
		}
	}

	public static Result logout(){
		session().clear();
		return redirect(routes.Login.index());
	}
}
