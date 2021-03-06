package controllers;

import java.util.*;

import controllers.Login.AuthLogin;
import models.GoodCount;
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
	public static Form<ThanksCard> bbsForm=Form.form(ThanksCard.class);

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

    	List<ThanksCard> thisMonthCard=ThanksCard.find.where().like("helpDate", la).eq("deleteRequest","0").findList();

        return ok(index.render(thisMonthCard,bbsForm,reSections,reName,seSections,seName,cate));
    }


    public static Result popup(Long cardID){

    	ThanksCard card = ThanksCard.find.byId(cardID);
    	//List<ThanksCard> ca=ThanksCard.find.where().eq("send.userName", card.send).findList();

    	return ok(popup.render(
    			//card.cardID,
    			card.helpDate,
    			card.receive.section.sectionName,
    			card.receive.userName,
    			card.category.categoryName,
    			card.helpText,
    			card.thanksText,
    			card.send.section.sectionName,
    			card.send.userName
    			));
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

    	List<ThanksCard> lastMonthCard=ThanksCard.find.where().like("helpDate", la).eq("deleteRequest","0").orderBy("good DESC").findList();
    	List <ThanksCard> las = new ArrayList<ThanksCard>();
    	for(int i = 0; i<1; i++){las.add(lastMonthCard.get(i));}
        return ok(lastMonth.render(las,bbsForm,reSections,reName,seSections,seName,cate));
    }

    public static Result search() {
//    	Map<String, String[]> params=request().body().asFormUrlEncoded();
//    	String d=params.get("helpDate")[0];
    	Form<ThanksCard> form=bbsForm.bindFromRequest();
		ThanksCard ca=form.get();
		List<ThanksCard> card=ThanksCard.find.all();
		//String day=ca.helpDate.toString();
//		d="%"+d+"%";
		List<ThanksCard> catSearch=new ArrayList<ThanksCard>();
		List<ThanksCard> dateSearch=new ArrayList<ThanksCard>();
		List<ThanksCard> recSecSearch=new ArrayList<ThanksCard>();
		if(ca.category.categoryID != null && ca.receive.userID != null){
			catSearch=ThanksCard.find.where().eq("category.categoryID", ca.category.categoryID).eq("receive.userID", ca.receive.userID).findList();
			return ok(search.render(catSearch));
		}

		if(ca.helpDate != null){
			dateSearch=ThanksCard.find.where().eq("helpDate", ca.helpDate).findList();
			return ok(search.render(dateSearch));
		}

		if(ca.receive.userID != null){
			recSecSearch=ThanksCard.find.where().eq("receive.userID", ca.receive.userID).eq("deleteRequest","0").findList();
			return ok(search.render(recSecSearch));
		}
		return ok(search.render(card));
    }


    public static Result good(Long cardID){

    	GoodCount count = new GoodCount();
    	ThanksCard card = ThanksCard.find.byId(cardID);
    	User user = User.find.where().eq("userCD", session("login")).findUnique();
    	count.cards = card;
    	count.user = user;
		GoodCount delcount = GoodCount.find.where().eq("cards.cardID", cardID).eq("user.userID", user.userID).findUnique();



    	if(delcount != null){
    		delcount.delete();
    		card.good -=1;
    		card.save();
    	}else{
    		card.good +=1;
    		card.save();
    		count.save();

    	}
    	return redirect(routes.Bbs.index());
    }


    public static Result detail(){

    	return TODO;
    }

}