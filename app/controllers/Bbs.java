package controllers;

import java.util.List;
import models.ThanksCard;
import play.*;
import play.mvc.*;
import views.html.bbs.*;

public class Bbs extends Controller {

    public static Result index() {
    	List<ThanksCard> card=ThanksCard.find.all();
        return ok(index.render(card));
    }
    public static Result lastMonth() {
        return ok(lastMonth.render());
    }

}
