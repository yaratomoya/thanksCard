package controllers;

import java.util.List;

import models.ThanksCard;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.mydata.*;

public class Mydata extends Controller {
	//public static

    public static Result index() {
    	Form<Mydata> mydataForm=Form.form(Mydata.class);
    	List<ThanksCard> card=ThanksCard.find.where().findList();
        return ok(index.render(mydataForm,card));
    }

    public static Result delete() {
        return ok("感謝カード送信完了");
    }

    public static Result popup() {
        return ok("感謝カードの削除を依頼しました。");
    }
}
