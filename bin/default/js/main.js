$(function () {
    //加载love;

    $.ajax({
        url: "/dash/getLove",
        type: "post",
        success: function (data) {
            if (data) {
                $("#J_container_love").prepend(data);

                //绑定动态事件
                $("div.blog-post").hover(
                    function () {
                        $(this).find("div.content-hide").slideToggle("fast");
                    },
                    function () {
                        $(this).find("div.content-hide").slideToggle("fast");
                    }
                );
            }
        }
    });


    //加载suisui
    $.ajax({
        url: "/dash/getNote",
        type: "post",
        success: function (data) {
            if (data) {
                $("#J_container_note").prepend(data);

                //激活动作
                $('.flexslider').flexslider({
                    prevText: '',
                    nextText: ''
                });

                $('.testimonails-slider').flexslider({
                    animation: 'slide',
                    slideshowSpeed: 5000,
                    prevText: '',
                    nextText: '',
                    controlNav: false
                });
            }
        }
    });

    //加载romeo
    $.ajax({
        url: "/dash/getRomeo",
        type: "post",
        success: function (data) {
            if (data) {
                $("#J_container_romeo").prepend(data);
            }
        }
    });

    //加载movies
    $.ajax({
        url: "/dash/getMovies",
        type: "post",
        success: function (data) {
            if (data) {
                $("#J_container_movies").prepend(data);
                /*电影轮播*/

                /***不需要自动滚动，去掉即可***/
                time = window.setInterval(function () {
                    $('.og_next').click();
                }, 5000);
                /***不需要自动滚动，去掉即可***/
                linum = $('.mainlist li').length;//图片数量
                w = linum * 250;//ul宽度
                $('.piclist').css('width', w + 'px');//ul宽度
                $('.swaplist').html($('.mainlist').html());//复制内容

                $('.og_next').click(function () {

                    if ($('.swaplist,.mainlist').is(':animated')) {
                        $('.swaplist,.mainlist').stop(true, true);
                    }

                    if ($('.mainlist li').length > 4) {//多于6张图片
                        ml = parseInt($('.mainlist').css('left'));//默认图片ul位置
                        sl = parseInt($('.swaplist').css('left'));//交换图片ul位置
                        if (ml <= 0 && ml > w * -1) {//默认图片显示时
                            $('.swaplist').css({left: '1000px'});//交换图片放在显示区域右侧
                            $('.mainlist').animate({left: ml - 1000 + 'px'}, 'slow');//默认图片滚动				
                            if (ml == (w - 1000) * -1) {//默认图片最后一屏时
                                $('.swaplist').animate({left: '0px'}, 'slow');//交换图片滚动
                            }
                        } else {//交换图片显示时
                            $('.mainlist').css({left: '1000px'})//默认图片放在显示区域右
                            $('.swaplist').animate({left: sl - 1000 + 'px'}, 'slow');//交换图片滚动
                            if (sl == (w - 1000) * -1) {//交换图片最后一屏时
                                $('.mainlist').animate({left: '0px'}, 'slow');//默认图片滚动
                            }
                        }
                    }
                })
                $('.og_prev').click(function () {

                    if ($('.swaplist,.mainlist').is(':animated')) {
                        $('.swaplist,.mainlist').stop(true, true);
                    }

                    if ($('.mainlist li').length > 4) {
                        ml = parseInt($('.mainlist').css('left'));
                        sl = parseInt($('.swaplist').css('left'));
                        if (ml <= 0 && ml > w * -1) {
                            $('.swaplist').css({left: w * -1 + 'px'});
                            $('.mainlist').animate({left: ml + 1000 + 'px'}, 'slow');
                            if (ml == 0) {
                                $('.swaplist').animate({left: (w - 1000) * -1 + 'px'}, 'slow');
                            }
                        } else {
                            $('.mainlist').css({left: (w - 1000) * -1 + 'px'});
                            $('.swaplist').animate({left: sl + 1000 + 'px'}, 'slow');
                            if (sl == 0) {
                                $('.mainlist').animate({left: '0px'}, 'slow');
                            }
                        }
                    }
                })


                $('.og_prev,.og_next').hover(function () {
                    $(this).fadeTo('fast', 1);
                }, function () {
                    $(this).fadeTo('fast', 0.7);
                })


                $(".og_next").click();

                /*电影轮播end*/
            }
        }
    });
});

