<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="author" content="www.qinco.net">
    <meta name="robots" content="index,follow,archive">
    <link rel="shortcut icon" href="/statics/img/favicon.ico">
    <title>NorthPark更新日志 | NorthPark</title>
    <meta name="keywords" content="NorthPark,更新日志">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>


</head>

<body style="">
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<div class="clearfix mainhead grayback">
    <div class="container">
        <div class="row margin-b20 margin-t20">
            <div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
                <div class="row margin-b20 margin-t20">
                    <h1>np-web</h1>
                    <p>np fix to elegance and add some new function fix serval bugs VERSION v3.1 - 2015年12月29日19:04:33
                        maven版本</p>
                    <h2>
                        2015年12月29日19:04:33 maven版本</h2>
                    <ul>
                        <li>
                            改进个人中心访问加载速度
                        </li>
                        <li>
                            重构了hibernate+springmvc配置 添加二级缓存和连接池控制
                        </li>
                    </ul>
                    <h2>
                        2015-12-30</h2>
                    <ul>
                        <li>
                            添加了二级缓存的实体映射
                        </li>
                        <li>
                            添加了时间处理
                        </li>
                        <li>
                            添加了responsebody返回中文utf8的配置
                        </li>
                        <li>
                            添加了junit4测试service调用
                        </li>
                        <li>
                            添加了pic详情页的查询和点赞处理
                        </li>
                    </ul>
                    <h2>
                        2016-01-04</h2>
                    <ul>
                        <li>
                            添加了memcached
                        </li>
                        <li>
                            添加了redis
                        </li>
                        <li>
                            添加了redis junit测试类测试通过
                        </li>
                    </ul>
                    <h2>
                        2016-01-08</h2>
                    <ul>
                        <li>
                            添加了淘宝支付热映电影
                        </li>
                        <li>
                            添加了电影管理
                        </li>
                    </ul>


                    <h2>Commits on Jan 3, 2016
                    </h2>
                    <li>
                        Update README.md
                        liuhouer committed on 3 Jan
                    </li>
                    3cbaa29
                    <h2>Commits on Dec 24, 2015
                    </h2>
                    <li>
                        add feed|rss |atom.xml fixcenter url ex
                        liuhouer committed on 24 Dec 2015
                    </li>
                    ea1772d
                    <h2>Commits on Dec 23, 2015
                    </h2>
                    <li>
                        x
                        liuhouer committed on 23 Dec 2015
                    </li>
                    50da727
                    </h2>
                    <li>
                        Merge branch 'master' of github.com:liuhouer/np
                        liuhouer committed on 23 Dec 2015
                    </li>
                    c4470d0
                    </h2>
                    <li>
                        ico changes
                        liuhouer committed on 23 Dec 2015
                    </li>
                    c394560
                    <h2>Commits on Dec 21, 2015
                    </h2>
                    <li>
                        x
                        liuhouer committed on 21 Dec 2015
                    </li>
                    480eed2
                    </h2>
                    <li>
                        去除图片的大小限制
                        liuhouer committed on 21 Dec 2015
                    </li>
                    f495a99
                    <h2>Commits on Dec 20, 2015
                    </h2>
                    <li>
                        Update README.md
                        liuhouer committed on 20 Dec 2015
                    </li>
                    db3d1cb
                    <h2>Commits on Dec 19, 2015
                    </h2>
                    <li>
                        little fixs
                        liuhouer committed on 19 Dec 2015
                    </li>
                    0ac193f
                    <h2>Commits on Dec 17, 2015
                    </h2>
                    <li>
                        修改公共位
                        liuhouer committed on 17 Dec 2015
                    </li>
                    fca862c
                    <h2>Commits on Dec 15, 2015
                    </h2>
                    <li>
                        修改添加个人主页的个性域名
                        liuhouer committed on 15 Dec 2015
                    </li>
                    f6986f7
                    <h2>Commits on Dec 14, 2015
                    </h2>
                    <li>
                        fix username email
                        liuhouer committed on 14 Dec 2015
                    </li>
                    0b6c093
                    </h2>
                    <li>
                        fix 3000pictures
                        liuhouer committed on 14 Dec 2015
                    </li>
                    761edf5
                    <h2>Commits on Dec 13, 2015
                    </h2>
                    <li>
                        修改view别人页面的分页
                        liuhouer committed on 13 Dec 2015
                    </li>
                    dfc5340
                    <h2>Commits on Dec 12, 2015
                    </h2>
                    <li>
                        第多少页
                        liuhouer committed on 12 Dec 2015
                    </li>
                    68a8193
                    </h2>
                    <li>
                        故事页链家修改
                        liuhouer committed on 12 Dec 2015
                    </li>
                    cfbf0c0
                    </h2>
                    <li>
                        分页更加优雅;添加了1000多条故事;添加了爬虫
                        liuhouer committed on 12 Dec 2015
                    </li>
                    4d10241
                    </h2>
                    <li>
                        add rich text and styled article and add article brief
                        liuhouer committed on 12 Dec 2015
                    </li>
                    aec9efd
                    <h2>Commits on Dec 10, 2015
                    </h2>
                    <li>
                        x
                        liuhouer committed on 10 Dec 2015
                    </li>
                    e673abd
                    </h2>
                    <li>
                        x
                        liuhouer committed on 10 Dec 2015
                    </li>
                    91f02b0
                    </h2>
                    <li>
                        xx
                        liuhouer committed on 10 Dec 2015
                    </li>
                    e9ad05d
                    </h2>
                    <li>
                        修改个人中心图片为圆形
                        liuhouer committed on 10 Dec 2015
                    </li>
                    2c01e49
                    <h2>Commits on Dec 9, 2015
                    </h2>
                    <li>
                        修改图片上传立即刷新
                        liuhouer committed on 9 Dec 2015
                    </li>
                    95b7405
                    </h2>
                    <li>
                        修改布图页面的排版style
                        liuhouer committed on 9 Dec 2015
                    </li>
                    8c1c3f6
                    </h2>
                    <li>
                        bug ifx
                        liuhouer committed on 9 Dec 2015
                    </li>
                    32d7e04
                    </h2>
                    <li>
                        修改信息bug fix
                        liuhouer committed on 9 Dec 2015
                    </li>
                    11468cf
                    <h2>Commits on Dec 3, 2015
                    </h2>
                    <li>
                        a
                        liuhouer committed on 3 Dec 2015
                    </li>
                    cd586c5
                    </h2>
                    <li>
                        add style tabs
                        liuhouer committed on 3 Dec 2015
                    </li>
                    8d8e67b
                    <h2>Commits on Dec 2, 2015
                    </h2>
                    <li>
                        remove url param links and add email reset pwd
                        liuhouer committed on 2 Dec 2015
                    </li>
                    a6251ca

                </div>
            </div>
        </div>
    </div>
</div>

<div class="clearfix maincontent">
    <div class="container">

    </div>

</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>


</body>
</html>
