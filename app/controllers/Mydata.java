package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.mydata.*;

public class Mydata extends Controller {
	public static Form<Mydata> mydataForm=Form.form(Mydata.class);

    public static Result index() {
        return ok(index.render(mydataForm));
    }

    public static Result delete() {
        return ok("感謝カード送信完了");
    }

    public static Result popup() {
        return ok("感謝カードの削除を依頼しました。");
    }
}
