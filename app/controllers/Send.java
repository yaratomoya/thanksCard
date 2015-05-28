package controllers;

import java.util.*;

import models.*;
import play.data.Form;
import play.mvc.*;
import views.html.send.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Send extends Controller {
	public static Form<Send> sendForm=Form.form(Send.class);
	public static Form<ThanksCard> sendForm2=Form.form(ThanksCard.class);

    public static Result index() {

    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }

        HashMap<String, String> reName=new HashMap<>();
        for(User user : User.find.all()){
        	reName.put(user.userID.toString(), user.userName);
        }

        HashMap<String, String> cate=new HashMap<>();
        	for(HelpCategory category : HelpCategory.find.all()){
        	cate.put(category.categoryID.toString(), category.categoryName);
        }

 //return ok(index.render(thisMonthCard,bbsForm,reSections));
        return ok(index.render(sendForm2,reSections,reName,cate));
    }

    public static Result create() {
    	Map<String, String[]> params = request().body().asFormUrlEncoded();

    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	ThanksCard card = new ThanksCard();

    	card.helpText = params.get("helpText")[0];

    	card.thanksText = params.get("thanksText")[0];

    	long recUser =  Integer.parseInt(params.get("receptionName")[0]);

    	long cateID = Integer.parseInt(params.get("category")[0]);

    	List<User> sendUser = User.find.where().eq("USER_CD", session("login")).findList();
    	User sendID2 = sendUser.get(0);
    	long sendID = sendID2.userID;

    	String helpdate = params.get("date")[0];

    	Date send_Date = new Date();

    	Date date = null;
		try {
			date = format.parse(helpdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	card.helpDate = date;

    	ThanksCard.create(card.helpText, card.thanksText, recUser,cateID, card.helpDate,sendID, send_Date);
        return redirect(routes.Send.index());
    }


}
