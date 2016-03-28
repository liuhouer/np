
package com.bruce.action;
/*
*@author bruce
*
**/
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bruce.manager.LyricsCommentManager;
import com.bruce.manager.LyricsManager;
import com.bruce.manager.LyricsZanManager;
import com.bruce.model.Lyrics;
import com.bruce.model.LyricsComment;
import com.bruce.model.LyricsZan;
import com.bruce.model.User;
import com.bruce.query.LyricsZanQuery;
import com.bruce.utils.TimeUtils;

@Controller
@RequestMapping("/zanAction")
@SessionAttributes({ "list", "lyricszan" })
public class LyricsZanAction {

 @Autowired	
 private LyricsZanManager lyricszanManager;
 @Autowired	
 private  LyricsCommentManager  plManager;
 @Autowired	
 private LyricsZanQuery lyricszanQuery;
 @Autowired	
 private LyricsManager lyricsManager;
 
 
 	@RequestMapping("/zan")
 	@ResponseBody
	public String zan(String lyricsid,String userid,HttpServletRequest request) {
 		
 		if(StringUtils.isEmpty(userid)){
            User u = (User) request.getSession().getAttribute("user");
            userid =  String.valueOf(u.getId());
        } 
 		String msg = "success";
		int num = this.lyricszanManager.findByCondition(" where lyricsid='"+lyricsid+"' and userid = '"+userid+"' ").getResultlist().size();
		if(num>0){
			msg = "zanguole";
		}else{
			try {
				
				LyricsZan model = new LyricsZan();
				model.setLyricsid(Integer.parseInt(lyricsid));
				model.setUserid(Integer.parseInt(userid));
				lyricszanManager.addLyricsZan(model);
				
				
				Lyrics lrc =  this.lyricsManager.findLyrics(Integer.parseInt(lyricsid));
				if(lrc!=null){
					int zannum = lrc.getZan()==null?0:lrc.getZan();
					zannum += 1;
					lrc.setZan(zannum);
					this.lyricsManager.updateLyrics(lrc);
				}
				msg = "success";
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exception";
			}
		}
		return msg;
	}
 	
 	@RequestMapping("/addComment")
 	@ResponseBody
	public String addComment(String comment,String userid,String lyricsid,HttpServletRequest request) {
 		if(StringUtils.isEmpty(userid)){
            User u = (User) request.getSession().getAttribute("user");
            userid = String.valueOf(u.getId());
        } 
 		
 		String msg = "success";
			try {
				
				LyricsComment model = new LyricsComment();
				model.setComment(comment);
				model.setUserid(Integer.parseInt(userid));
				model.setLyricsid(Integer.parseInt(lyricsid));
				model.setCreate_time(TimeUtils.nowTime());

				plManager.addLyricsComment(model);
				
				
				Lyrics lrc =  this.lyricsManager.findLyrics(Integer.parseInt(lyricsid));
				if(lrc!=null){
					int plnum = lrc.getPl()==null?0:lrc.getPl();
					plnum += 1;
					lrc.setPl(plnum);
					this.lyricsManager.updateLyrics(lrc);
				}
				msg = "success";
			} catch (Exception e) {
				// TODO: handle exception
				msg = "exception";
			}
		return msg;
	}

}
