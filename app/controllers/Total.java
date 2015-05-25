package controllers;

import play.*;
import play.mvc.*;

import views.html.total.*;

public class Total extends Controller {

    public static Result index() {
        return ok(index.render());
    }

}
