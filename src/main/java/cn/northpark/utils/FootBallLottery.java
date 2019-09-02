package cn.northpark.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FootBallLottery {
	
	
	public static void main(String[] args) {
		//1.主网页      http://live.win007.com/index2in1.aspx?id=12	
		
		String homeUrl = "http://live.win007.com/index2in1.aspx?id=12";
		String dataResult = HttpGetUtils.getDataByHtmlUnit(homeUrl);

        log.info(dataResult);
		
		//2.数据网页  http://vip.win007.com/OverDown_n.aspx?id=1786684
	}
}
