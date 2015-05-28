package controllers;

import java.util.*;

import models.ThanksCard;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.bbs.*;

public class Bbs extends Controller {
	public static Calendar cal=Calendar.getInstance();


    public static Result index() {
    	int th=cal.get(Calendar.MONTH)+1;
    	String l=String.valueOf(th);
    	String la;
    	if(th<10){
    		la="%0"+l+"%";
    	}else{
    		la="%"+l+"%";
    	}
    	List<ThanksCard> thisMonthCard=ThanksCard.find.where().like("helpDate", la).findList();
    	Form<Bbs> bbsForm=Form.form(Bbs.class);
        return ok(index.render(thisMonthCard,bbsForm));
    }


    public static Result popup() {
    	List<ThanksCard> card=ThanksCard.find.all();
        return ok("日付 感謝された人（部署・名前）ヘルプカテゴリ してもらったこと 感謝の言葉 感謝された人（部署・名前）ＯＫボタン");
    }


    public static Result lastMonth() {
    	int last=cal.get(Calendar.MONTH);
    	int year=cal.get(Calendar.YEAR)-1;
    	String l=String.valueOf(last);
    	String y=String.valueOf(year);
    	String la;
    	if(last==1){
    		la=y+"-"+l+"%";
    	}
    	if(last<10){
    		la="%0"+l+"%";
    	}else{
    		la="%"+l+"%";
    	}
    	List<ThanksCard> lastMonthCard=ThanksCard.find.where().like("helpDate", la).findList();
        return ok(lastMonth.render(lastMonthCard));
    }

    public static Result search() {

        return ok("検索結果");
    }

}
