package controllers;

import play.*;
import play.mvc.*;

import views.html.login.*;

public class Login extends Controller {

    public static Result index() {
        return ok(index.render());
    }

}
