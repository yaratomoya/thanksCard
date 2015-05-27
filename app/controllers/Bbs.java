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

    public static Result popup() {
    	List<ThanksCard> card=ThanksCard.find.all();
        return ok("日付 感謝された人（部署・名前）ヘルプカテゴリ してもらったこと 感謝の言葉 感謝された人（部署・名前）ＯＫボタン");
    }

    public static Result lastMonth() {
        return ok(lastMonth.render());
    }

}
