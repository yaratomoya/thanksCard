package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Section;
import models.ThanksCard;
import models.User;
import play.mvc.*;
import play.data.Form;
import views.html.total.*;

public class Total extends Controller {
	public static Form<ThanksCard> totalForm=Form.form(ThanksCard.class);


    public static Result index() {
    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }

        List<ThanksCard> dateSearch=ThanksCard.find.where().eq("deleteRequest", 0).findList();
        List<ThanksCard> good_cnt;
        List<User> user=User.find.all();

        ArrayList<Integer> user2 = new ArrayList<Integer>();
        ArrayList<String> user2_st = new ArrayList<String>();
        User user3 = user.get(0);
        User t_user;
        ThanksCard t_count;
        int kazu = 0, count = 0;

        int[][] arr = new int[user.size()][2];

        //ソート用の配列
        for(int i=0; i<user.size(); i++){
        	kazu = i;
        	user3 = user.get(kazu);
        	user2.add(ThanksCard.find.where().eq("receive.userID", user3.userID).eq("deleteRequest", 0).findRowCount());
        	arr[i][0] = user2.get(i);
        	arr[i][1] = new Integer(user3.userID.toString());

        }
        MyComparator comparator = new MyComparator(0);
        Arrays.sort(arr, comparator);
        user2.clear();

        //ソートした値を代入
        for(int i=0; i<user.size(); i++){
        	String uid_st = String.valueOf(arr[i][1]);
        	long uid = Integer.parseInt(uid_st);
        	count=0;

        	t_user = User.find.byId(uid);
        	user2_st.add(t_user.userName);
        	user2.add(arr[i][0]);

        	good_cnt = ThanksCard.find.where().eq("receive.userID", uid).eq("deleteRequest", 0).findList();
        	for(int j=0; j<good_cnt.size(); j++){
        		t_count = good_cnt.get(j);
        		count += t_count.good;
        	}
        	user2.add(count);
        }

        Iterator<Integer> it = user2.iterator();
        Iterator<String> it2 = user2_st.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it,it2));
    }

    public static Result search(){
    	HashMap<String, String> reSections=new HashMap<>();
        for(Section section : Section.find.all()){
            reSections.put(section.sectionID.toString(), section.sectionName);
        }

        //選んだ部署IDの取得と検索
        Map<String, String[]> params = request().body().asFormUrlEncoded();
        long recID;
        //部署指定したか判定
        if(params.get("sectionfind")[0] != ""){
        	recID = Integer.parseInt(params.get("sectionfind")[0]);
        }else{
        	return redirect(routes.Total.index());
        }

        List<ThanksCard> dateSearch=ThanksCard.find.where().eq("receive.section.sectionID", recID).eq("deleteRequest", 0).findList();
        List<ThanksCard> good_cnt;
        List<User> user=User.find.where().eq("section.sectionID", recID).findList();

        ArrayList<Integer> user2 = new ArrayList<Integer>();
        ArrayList<String> user2_st = new ArrayList<String>();
        User user3 = user.get(0);
        User t_user;
        ThanksCard t_count;
        int kazu = 0, count = 0;

        int[][] arr = new int[user.size()][2];

        for(int i=0; i<user.size(); i++){
        	kazu = i;
        	user3 = user.get(kazu);
        	user2.add(ThanksCard.find.where().eq("receive.userID", user3.userID).eq("deleteRequest", 0).findRowCount());
        	arr[i][0] = user2.get(i);
        	arr[i][1] = new Integer(user3.userID.toString());
        }
        MyComparator comparator = new MyComparator(0);
        Arrays.sort(arr, comparator);
        user2.clear();

        for(int i=0; i<user.size(); i++){
        	String uid_st = String.valueOf(arr[i][1]);
        	long uid = Integer.parseInt(uid_st);
        	count=0;

        	t_user = User.find.byId(uid);
        	user2_st.add(t_user.userName);
        	user2.add(arr[i][0]);

        	good_cnt = ThanksCard.find.where().eq("receive.userID", uid).eq("deleteRequest", 0).findList();
        	for(int j=0; j<good_cnt.size(); j++){
        		t_count = good_cnt.get(j);
        		count += t_count.good;
        	}
        	user2.add(count);
        }

        Iterator<Integer> it = user2.iterator();
        Iterator<String> it2 = user2_st.iterator();
        return ok(index.render(totalForm,reSections,dateSearch,user,it,it2));
    }

    static class MyComparator implements Comparator<int[]> {
        public int sortIndex = 0;
        public MyComparator(int sortIndex) {
            this.sortIndex = sortIndex;
        }

        //比較
        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[sortIndex] - o1[sortIndex];
        }
    }
}
