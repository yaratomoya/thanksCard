package controllers;

import java.util.List;

import models.ThanksCard;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.mydata.*;

public class Mydata extends Controller {
    public static Result index() {
    	Form<ThanksCard> mydataForm=Form.form(ThanksCard.class);
    	List<ThanksCard> reCard=ThanksCard.find.where().eq("receive.userCD", session("login")).eq("deleteRequest", 0).findList();
    	List<ThanksCard> seCard=ThanksCard.find.where().eq("send.userCD", session("login")).eq("deleteRequest", 0).findList();
        return ok(index.render(mydataForm,reCard,seCard));
    }

    public static Result delete(Long cardID) {

    	ThanksCard card = ThanksCard.find.byId(cardID);
    	card.deleteRequest = 1;
    	card.update();

        return redirect(routes.Mydata.index());

    }

    public static Result popup() {
        return ok("感謝カードの削除を依頼しました。");
    }
}
