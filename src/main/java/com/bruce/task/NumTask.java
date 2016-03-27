package com.bruce.task;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.manager.LyricsCommentManager;
import com.bruce.manager.LyricsManager;
import com.bruce.manager.LyricsZanManager;
import com.bruce.model.Lyrics;
import com.bruce.utils.TimeUtils;

/**
 * @author zhangyang
 *
 * 定时更新赞和评论值
 */
public class NumTask {

	@Autowired
	private LyricsManager lyricsManager;
	
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
		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
}
