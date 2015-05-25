package controllers;

import play.*;
import play.mvc.*;

import views.html.send.*;

public class Send extends Controller {

    public static Result index() {
        return ok(index.render());
    }

}
