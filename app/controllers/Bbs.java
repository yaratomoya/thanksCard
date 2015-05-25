package controllers;

import play.*;
import play.mvc.*;

import views.html.bbs.*;

public class Bbs extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    public static Result lastMonth() {
        return ok(lastMonth.render());
    }

}
