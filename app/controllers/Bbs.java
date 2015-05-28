package controllers;

import java.util.*;

import models.HelpCategory;
import models.Section;
import models.ThanksCard;
import models.User;
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

    	HashMap<String, String> reSections=new HashMap<>();
    	for(Section section : Section.find.all()){
    		reSections.put(section.sectionID.toString(), section.sectionName);
    	}

    	HashMap<String, String> reName=new HashMap<>();
    	for(User user : User.find.all()){
    		reName.put(user.userID.toString(), user.userName);
    	}

    	HashMap<String, String> seSections=new HashMap<>();
    	for(Section section : Section.find.all()){
    		seSections.put(section.sectionID.toString(), section.sectionName);
    	}

    	HashMap<String, String> seName=new HashMap<>();
    	for(User user : User.find.all()){
    		seName.put(user.userID.toString(), user.userName);
    	}

    	HashMap<String, String> cate=new HashMap<>();
    	for(HelpCategory category : HelpCategory.find.all()){
    		cate.put(category.categoryID.toString(), category.categoryName);
    	}

    	List<ThanksCard> thisMonthCard=ThanksCard.find.where().like("helpDate", la).findList();
    	Form<ThanksCard> bbsForm=Form.form(ThanksCard.class);
        return ok(index.render(thisMonthCard,bbsForm,reSections,reName,seSections,seName,cate));
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
