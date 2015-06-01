package controllers;

import java.util.*;

import models.ThanksCard;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.managebbs.*;

public class ManageBbs extends Controller {



    public static Result index() {
		Form<ThanksCard> managebbsForm=Form.form(ThanksCard.class);
    	List<ThanksCard> card=ThanksCard.find.where().eq("deleteRequest",1).findList();
        return ok(index.render(card,managebbsForm));

    }

    public static Result delete(Long cardID){

    	ThanksCard card = ThanksCard.find.byId(cardID);
    	card.deleteRequest = 2;
    	card.update();

    	return redirect(routes.ManageBbs.index());
    }


}
