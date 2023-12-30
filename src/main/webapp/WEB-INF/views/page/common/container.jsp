<%@ page pageEncoding="UTF-8" %>

<footer class="mainfoot center ">

    <div class="row">

        <div class="col-md-12">

            <p1>

                2014 © <span class="glyphicon glyphicon-heart"></span> NorthPark. ALL Rights Reserved.

            </p1>

            <p2>

            <%--  <a target="_blank" href="http://know.NorthPark.cn" rel="nofollow" title="NorthPark知识共享">NorthPark知识共享<span class="badge green-badge">new</span></a>--%>

                <a target="_blank" href="http://blog.NorthPark.cn" rel="nofollow" title="NorthPark博客">NorthPark博客</a>

                <a target="_blank" href="/poem/index.html" title="诗词秀">诗词秀</a>

                <a target="_blank" href="/romeo" title="情圣时刻">情商提升</a>

                <a target="_blank" href="/cm/x_b_j_t" title="2011年学生时代-原创音乐播放器">小布静听</a>

                <a target="_blank" href="/donate" title="赞助本站">赞助我们</a>

                <a target="_blank" href="/about.html" title="关于NorthPark" class="aend">关于NorthPark</a>

                <!-- <a target="_blank"  href="/wx/astro" title="星座运势、人性化的美少女塔罗牌消息私人订制"  class="aend" >星座美少女</a> -->

            </p2>

        </div>

    </div>

</footer>

<script src="https://northpark.cn/statics/js/jquery-1.7.2.js"></script>

<script src="https://northpark.cn/statics/js/bootstrap.min.js"></script>

<script src="https://northpark.cn/statics/js/artDialog/artDialog.js?skin=default"></script>

<script src="https://northpark.cn/statics/js/artDialog/jquery.artDialog.js?skin=default"></script>

<script src="https://northpark.cn/statics/js/artDialog/plugins/iframeTools.js"></script>

<script data-cfasync="false" src="https://northpark.cn/statics/js/page/seltab.js"></script>

<script data-cfasync="false" src="https://northpark.cn/statics/js/page/common.js"></script>

<%--<script data-cfasync="false" src="/static/js/page/common.js"></script>--%>

<%--google ads--%>
<%--<script data-ad-client="ca-pub-6480430202155588" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>--%>

<script>

    (function (i, s, o, g, r, a, m) {

        i['GoogleAnalyticsObject'] = r;

        i[r] = i[r] || function () {

            (i[r].q = i[r].q || []).push(arguments)

        }, i[r].l = 1 * new Date();

        a = s.createElement(o),

            m = s.getElementsByTagName(o)[0];

        a.async = 1;

        a.src = g;

        m.parentNode.insertBefore(a, m)

    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-87600630-1', 'auto');

    ga('send', 'pageview');

</script>


<%--Bing Clarity--%>
<script type="text/javascript">
    (function(c,l,a,r,i,t,y){
        c[a]=c[a]||function(){(c[a].q=c[a].q||[]).push(arguments)};
        t=l.createElement(r);t.async=1;t.src="https://www.clarity.ms/tag/"+i;
        y=l.getElementsByTagName(r)[0];y.parentNode.insertBefore(t,y);
    })(window, document, "clarity", "script", "jtdwep36w2");
</script>


<script>

    /**
     * 刷新未读
     */
    $(function (){
        var u = '${user.id}';
        if (u) {
            var notifyCount = getNotifyCount();
            $("#J_notify_box").text(notifyCount);

            if(notifyCount>0){
                $('title').text('NORTHPARK（'+notifyCount+'）');
            }


        }
    })
</script>

<!-- Cloudflare Web Analytics -->

<script defer src='https://static.cloudflareinsights.com/beacon.min.js' 

data-cf-beacon='{"token": "77408708315e467b876ee630114c6196"}'>

</script>

<!-- End Cloudflare Web Analytics -->

<%--自动登录逻辑start--%>
<script>

    //自动登录

    function autoLogin(){
        $.ajax({
            url: "/cm/autoLogin",
            type: "get",
            dataType: "json",
            success: function (msg) {

                if(msg.result){
                    //自动登陆成功
                    console.log(msg.data);
                    art.dialog.tips(msg.data+' | 必要时刷新页面');

                    //拉取未读消息数量
                    fetchNotifyCount();


                }else{
                    console.log('自动登录失败--->'+msg.message);
                }
            }
        });

    }

    function setCookie(cname, cvalue, exsec){
        var d = new Date();
        d.setTime(d.getTime()+(exsec*1000));
        var expires = "expires="+d.toGMTString();
        document.cookie = cname+"="+cvalue+"; "+expires+";path=/;";
    }

    function getCookie(cname){
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
        }
        return "";
    }

    $(function (){
        var autoed = getCookie("autoed");
        var user = "${user.id}";
        if (!autoed && !user) {

            autoLogin();

            //已登录标识
            setCookie("autoed", true, 60*5 );
        }
    })
</script>

<%--自动登录逻辑end--%>

