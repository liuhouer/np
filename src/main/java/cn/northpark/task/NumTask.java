package cn.northpark.task;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.manager.LyricsManager;
import cn.northpark.manager.LyricsZanManager;
import cn.northpark.manager.UserManager;
import cn.northpark.model.Lyrics;
import cn.northpark.model.User;
import cn.northpark.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时更新赞和评论值
 */
public class NumTask {

	@Autowired
	private LyricsManager lyricsManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private LyricsCommentManager plManager;
	
	@Autowired
	private LyricsZanManager zanManager;
	
	public void runTask(){
		System.out.println("定时任务开始"+TimeUtils.getNowTime());
		
		
		
		//更新图片地址
		List<Lyrics> list = lyricsManager.findAll();
		
		
		if(list!=null){
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					
					//批量处理图片的路径
					String imgpath = (String) list.get(i).getAlbumImg(); //e:/yunlu/upload/1399976848969.jpg
					if(!StringUtils.isEmpty(imgpath)){
					String[] str = imgpath.split("/album/");
					if(str.length>1){
					String imgp = "album/"+str[1];
					list.get(i).setAlbumImg(imgp);
					}
					}
					
					//批量处理赞和评论的个数
					String lyricsid = String.valueOf(list.get(i).getId());
					int zan =  zanManager.getZanNumByLRC(lyricsid);
					int pl  =  zanManager.getCommentNumByLRC(lyricsid);
					list.get(i).setZan(zan);
					list.get(i).setPl(pl);
					
					//批量处理当前用户对这个pic/歌词的赞状态
					
					
					//更新
					lyricsManager.updateLyrics(list.get(i));
					
				}
			}
		}
		
		
		//更新头像地址
		List<User> userlist = userManager.findAll();
		for (User u:userlist) {
			 String imgpath_ =u.getHeadpath();
			 if(!StringUtils.isEmpty(imgpath_)){
				 String[] str = imgpath_.split("heads/");
				 if(str.length>1){
					 String imgp = "heads/"+str[1];
					 u.setHeadpath(imgp);
					 userManager.updateUser(u);
				 }
			 }
		}
		
		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
}
