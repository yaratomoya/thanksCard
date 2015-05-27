package controllers;

import java.util.*;
import models.ThanksCard;
import play.*;
import play.mvc.*;
import views.html.bbs.*;

public class Bbs extends Controller {
	public static Calendar cal=Calendar.getInstance();

    public static Result index() {
    	List<ThanksCard> thisMonthCard=ThanksCard.find.where().like("helpDate", "%05%").findList();
        return ok(index.render(thisMonthCard));
    }

    public static Result lastMonth() {
    	//String last="%" + cal.add(Calendar.MONTH, -1) + "%";
    	List<ThanksCard> lastMonthCard=ThanksCard.find.where().like("helpDate", "%04%").findList();
        return ok(lastMonth.render(lastMonthCard));
    }

}
