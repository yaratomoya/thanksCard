package controllers;

import models.User;
import play.*;
import play.mvc.*;
import views.html.*;

public class Categorys extends Controller {

    public static Result index() {
        return ok(index.render("category"));
    }

}