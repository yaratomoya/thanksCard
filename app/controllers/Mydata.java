package controllers;

import play.*;
import play.mvc.*;

import views.html.mydata.*;

public class Mydata extends Controller {

    public static Result index() {
        return ok(index.render());
    }
}
