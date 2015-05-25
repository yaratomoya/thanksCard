package controllers;

import play.*;
import play.mvc.*;

import views.html.managebbs.*;

public class ManageBbs extends Controller {

    public static Result index() {
        return ok(index.render());
    }

}
