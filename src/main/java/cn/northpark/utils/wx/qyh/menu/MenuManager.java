package cn.northpark.utils.wx.qyh.menu;

/**
 * 自定义菜单管理器类 
 * 
 * @author ivhhs
 * @date 2014.10.16
 */
import cn.northpark.utils.wx.qyh.WeixinQyhUtil;
import cn.northpark.utils.wx.qyh.ParamesAPI.AccessToken;
import cn.northpark.utils.wx.qyh.ParamesAPI.ParamesAPI;

public class MenuManager {



	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		
		ViewButton btn11 = new ViewButton();
		btn11.setName("今日运势");
		btn11.setType("view");
		btn11.setUrl("http://northpark.cn/wx/astro?type=today");
		
		ViewButton btn12 = new ViewButton();
		btn12.setName("明日运势");
		btn12.setType("view");
		btn12.setUrl("http://northpark.cn/wx/astro?type=tommorrow");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("本周运势");
		btn13.setType("view");
		btn13.setUrl("http://northpark.cn/wx/astro?type=week");


		ViewButton btn14 = new ViewButton();
		btn14.setName("本月运势");
		btn14.setType("view");
		btn14.setUrl("http://northpark.cn/wx/astro?type=month");



		CommonButton btn21 = new CommonButton();
		btn21.setName("天气");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("PM2.5");
		btn22.setType("click");
		btn22.setKey("22");



		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("星座运势");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("天气环境");
		mainBtn2.setSub_button(new Button[] { btn21, btn22});


		/**
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });

		return menu;
	}
	
	
	public static void main(String[] args) {
		

		// 调用接口获取access_token
		AccessToken at = WeixinQyhUtil.getAccessToken(ParamesAPI.corpId, ParamesAPI.secret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinQyhUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result) {
				System.out.println("菜单创建成功！");
			} else
				System.out.println("菜单创建失败！");
		}
	}
	
	
}
