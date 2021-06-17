# np-web

## 2021年6月17日 晚
- 批量轉移七牛違規圖片+批量刪除函數編寫

## 2021年5月15日
- 添加资源失效反馈功能，一键反馈邮件给站长处理，相当便捷

## 2021年2月7日
- 1.更新了60页电影
- 2.更新了40页电视剧集
- 3.更新了20页动漫剧集
- 4.更新了电影sitemap

## 2021年01月09日 
- 1.美团红包 
- 2.饿了么红包 
- 3.饿了么果蔬 
>TODO 
>>1.美团果蔬

## 2019年7月23日
> 不知道哪个狗东西把我的数据库删了，草拟吗！
>> 所有数据恢复到7.10号，狗东西的存在让我弄个备份脚本天天备份。
>>> 懒惰是我的问题，自动化备份马上就好

## 2019年7月10日
- 删除druid数据源
- 配置hikari数据源
- 添加数据操作工具类
- 重新生成sitemap
- 优化数据源的配置


## 2019年6月27日
- 服务器IP被封了，暂时不续费了，等待后续墙环境好些再说。

## 2019年5月02日
- 服务器欠费了，暂时关闭了，搞不起了
- 备份数据 google3_20190502103459.tgz
- 备份配置文件 docker nginx redis tomcat netty 

## 2019年1月22日
- 准备开发一个知识共享网站，搜集各种知识资料书籍
- 用know.northpark.cn吧
- 页面简介优雅白色系

## 2018年12月14日
- docker: tomcat8.5集群 与 redis session 共享

```

<Context>
    <!-- Default set of monitored resources -->
    <WatchedResource>WEB-INF/web.xml</WatchedResource>

    <!-- Uncomment this to disable session persistence across Tomcat restarts -->
    <!--
    <Manager pathname="" />
    -->

    <!-- Uncomment this to enable Comet connection tacking (provides events
         on session expiration as well as webapp lifecycle) -->
    <!--
    <Valve className="org.apache.catalina.valves.CometConnectionManagerValve" />
    -->
<Valve className="com.orangefunction.tomcat.redissessions.RedisSessionHandlerValve" />
<Manager className="com.orangefunction.tomcat.redissessions.RedisSessionManager"
		   host="localhost"  
		   port="6379"  
		   password="caonima" 
		   database="0"  
		   maxInactiveInterval="60" />  
</Context>
```

## 2018年11月15日
- docker
 - ElasticSearch

	*  海量数据分析引擎
	*  站内搜索 引擎
	*  数据仓库

  

## 2018年11月1日
- 添加rabbitMQ来处理邮件通知

## 2018年10月11日
- 优化注册登录的体验【跳转丝滑顺畅】
- zookeeper配置分布式锁

## 2018年10月11日
- 优化了消息转换器的配置
- 优化了登录和异常的体验
- ajax异步处理更加优雅了

## 2018年10月8日
- pom文件修改和maven配置优化

## 2018年7月4日
- https://northpark.cn/sitemap/soft.xml
- https://northpark.cn/sitemap/movies.xml
- https://northpark.cn/sitemap/poem.xml
- https://northpark.cn/sitemap/love.xml
- https://northpark.cn/sitemap/qingsheng.xml

## 2018年3月23日
> 最近更新了mac资源、生成了sitemap并没有seo优化的太大作用fk

## 2017年12月5日
> 删除download lrc
> 删除view lrc
> 删除lyrics表不需要的字段
> 删除user表不需要的字段
> 更改zancommet的url
> 个人中心的最爱添加点赞的记录
> 个人中心添加网址和简介在个人栏




###数据问题
> 构造碎碎念的作者为图册创建者的id
> 构造关注作者
> 主题图册的图片替换
> 重新让百度google bing收录


## 2017年12月5日
- 更新情圣爬虫
- 情圣页面添加赞赏、评论
- 去掉没用的引入

## 2017年11月20日

- 升级tomcat8最新版本
- 升级jdk8新版本
- 优化tomcat8 的connector为apr连接
- 优化jdk8 的jvm分配
- 大量性能优化、启用浏览器压缩等等

### 近期优化目录
>情圣页面 --一次查询出来  搜索异步查询

>碎碎念页面  --2次查询

>最爱页面  --2次查询

>诗词秀  --1次查询


>电影页面   --1次查询

>mac软件页面 --1次查询


>电影页面搜索优化  非表单提交式搜索

>mac软件页面搜索优化  非表单提交式搜索



>最爱评论点赞页面   全面优化 整合成一个sql   【/comment/{lyricsid}.html】


>最爱评论点赞页面  留言异步加载重写 优雅的实现



>诗词秀页面搜索   非表单提交式搜索


>情圣页面搜索优化  非表单提交式搜索

>page页面请求的命名优雅修改



>web.xml优化

>tomcat优化 连接

>java版本升级优化


## 2017年11月17日

### 分页重新架构

- 情圣页面 --一次查询出来  搜索异步查询

- 碎碎念页面  --2次查询

- 最爱页面  --2次查询

- 诗词秀  --1次查询

- 电影页面   --1次查询

- mac软件页面 --1次查询

- 电影页面搜索优化  非表单提交式搜索

- mac软件页面搜索优化  非表单提交式搜索


- 最爱评论点赞页面   全面优化 整合成一个sql   

- 最爱评论点赞页面  留言异步加载重写 优雅的实现

- 诗词秀页面搜索   非表单提交式搜索


- 情圣页面搜索优化  非表单提交式搜索

- page页面请求的命名优雅修改

- web.xml优化


## 2017年11月9日
- 去掉没用的action和方法
- 去掉没用的工具类
- 去掉没用到jar包
- 准备去掉所有不用的测试类，仅有的上线功能保留在master分支，下线的功能都去掉
- full分支来开发测试稀奇功能和工具类的编写调试

## 2017年7月27日
- 修复软件自动更新功能
- 爬取了5.16号以来所有Apple软件

## 2017年7月11日
- 添加管理员一键置顶热门电影、+隐藏大尺度电影的功能
- 更新了所有的院线热门电影进来

## 2017年4月24日
- 在线音乐播放器alpha功能基本完成

## 2017年4月10日
- 诗词秀功能基本完成，上线


## 2017年3月14日
- 添加电影频道自动更新资源
- 添加电影限制级过滤
- 电影筛选页面排版样式优化
- 去掉无效的文章爬取


## 2017年3月08日
- 更新电视剧全部系列
- 更新动漫频道全部系列


## 2017年3月07日
- 更新电影《一条狗的使命》《爱乐之城》《生化危机6-终章》《月光男孩》《金刚狼3》《天才捕手》《欢乐好声音》...等三月份院线上映大片
- 更新爬虫逻辑，防止IP封锁


## 2017年1月24日
- 网站【关于我们】功能上线



## 2017年1月6日
- 倒腾了大半天，总算搞成了https请求。请求更安全了~~~ 赞！

## 2017年1月5日
- 影视窝改版上线~~
- TODO. 继续爬虫剩下的资源，调整样式和格式

## 2016-12-26 16:56:02
- 添加 夏目友人帐资源1-5季全集

## 2016-10-08 12:45:11
- 首页缓存去除、优化速度加载
- 电影添加分页
- bug fix



## 2016-08-18
- 新写了一套登录和注册的样式，更加可爱 & 小清新


## 2016-07-15
-  更新包名为网站的番号 版本更新为3.5
-  ajax 加载更多加载评论


## 2016-07-14
-  修改所有的默认头像为谷歌MD设计的首字母带阴影头像


## 2016-07-07
-  添加微信的常见所有开发情景处理和二维码生成的工具类



## 2016-4-13
- 迁移到coding.net,build path 各种恶心的问题修复，重新部署

## 2016年04月10日
- 简化注册登陆流程，统一样式并且大大简化添加主题步骤

## 2016-4-8
- 免费电影了 | 红包优化修改

## 2016-03-24

- np3.2版本之前的存档分支。ID策略为UUID。
- 下个版本在主干，ID策略改为native。
- 完成uuid--->native重构


## 2016-03-07
- 修改图片显示问题
- 修改底部链接
- 添加更新日志

## 2016-01-08
- 添加了淘宝支付热映电影
- 添加了电影管理


## 2016-01-04
- 添加了memcached
- 添加了redis
- 添加了redis junit测试类测试通过

## 2015-12-30
- 添加了二级缓存的实体映射
- 添加了时间处理
- 添加了responsebody返回中文utf8的配置
- 添加了junit4测试service调用
- 添加了pic详情页的查询和点赞处理



## 2015年12月29日19:04:33 maven版本
- 改进个人中心访问加载速度
- 重构了hibernate+springmvc配置 添加二级缓存和连接池控制

## 2015年5月12日
- 购买域名northpark，正式上线



## 2014年6月4日
- IP上线第一个版本，包含图册、碎碎念、个人中心功能





# np
***之前的***
-  np fix to elegance and add some new function fix serval bugs VERSION v3.0 2015-12-01 11:56:55


-  1月3日 休息了几天。。。回家没有了雾霾，暂时的安静

-  好几天不来，仿佛过了一个世纪~

-  放水专用时间戳 7.8-7.11
