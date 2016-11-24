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
		CommonButton btn11 = new CommonButton();
		btn11.setName("今日运势");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("明日运势");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("本周运势");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("本月运势");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn15 = new CommonButton();
		btn15.setName("今年运势");
		btn15.setType("click");
		btn15.setKey("15");

		
		CommonButton btn21 = new CommonButton();
		btn21.setName("天气");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("PM2.5");
		btn22.setType("click");
		btn22.setKey("22");



		ViewButton btn31 = new ViewButton();
		btn31.setName("学霸授权");
		btn31.setType("view");
		btn31.setUrl("http://112.124.111.3/jialian/");

		CommonButton btn32 = new CommonButton();
		btn32.setName("学霸快递");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("学霸笑话");
		btn33.setType("click");
		btn33.setKey("33");

		ViewButton btn34 = new ViewButton();
		btn34.setName("学霸微网");
		btn34.setType("view");
		btn34.setUrl("http://112.124.111.3/jialian/");

		CommonButton btn35 = new CommonButton();
		btn35.setName("图片测试");
		btn35.setType("click");
		btn35.setKey("35");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("星座运势");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn15 });

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
