package controllers;
import play.data.Form;

import play.*;
import play.mvc.*;

import views.html.login.*;


public class Login extends Controller {
    public static class Logins {
       public String SyainID;
       public String PassWord;
    }

	 public static Form<Login> loginForm = Form.form(Login.class);


    public static Result index() {
        return ok(index.render(loginForm));
    }
    public static Result authenticate() {
    	Login login = loginForm.bindFromRequest().get();
    	return ok("ログインしたユーザは です");

    }

}
