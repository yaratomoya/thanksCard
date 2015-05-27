package controllers;

import play.*;
import play.mvc.*;

import views.html.mydata.*;

public class Mydata extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result popup() {
        return ok("感謝カードの削除を依頼しました。");
    }
}
