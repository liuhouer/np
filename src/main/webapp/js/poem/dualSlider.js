
(function($) {
    $.fn.dualSlider = function(options) {
        var defaults = {
            auto: true,
            autoDelay: 10000,
            easingCarousel: 'swing',
            easingDetails: 'easeOutBack',
            durationCarousel: 1000,
            durationDetails: 600
        };

        var options = $.extend(defaults, options);

        this.each(function() {

            var obj = $(this);
            var carousel;
            var carouselTotal = $(".backgrounds", obj).children().length;
            var carouselPosition = 1;
            var carouselLinkIndex = 1;
            var carouselLinks = "";
            var carouselwidth = $(obj).width();
            var detailWidth = $(".panel .details_wrapper", obj).width();
            var locked = false;
			
			if(options.auto == true)
			{
				//Creat duplicates for seamless looping
				$(".backgrounds", obj).prepend($(".backgrounds .item:last-child", obj).clone().css("margin-left", "-" + carouselwidth + "px"));
				$(".backgrounds", obj).append($(".backgrounds .item:nth-child(2)", obj).clone());

				$(".details", obj).prepend($(".details .detail:last-child", obj).clone().css("margin-left", "-" + detailWidth + "px"));
				$(".details", obj).append($(".details .detail:nth-child(2)", obj).clone());
			}
			else{
				$(".previous", obj).hide();
				$(".play, .pause", obj).hide();
			}


            //Set main background width
            $(".backgrounds", obj).css("width", ((carouselTotal+1) * carouselwidth) + 100 + "px");
            

            //Set main detail width
            $(".details_wrapper .details", obj).css("width", ((carouselTotal+1) * carouselwidth)  + "px");

            for (i = 1; i <= carouselTotal; i++) {
                (i == 1) ? carouselLinks += "<a rel=\"" + carouselLinkIndex + "\" title=\"97站长网" + carouselLinkIndex + " \" class=\"link" + carouselLinkIndex + " selected\" href=\"javascript:void(0)\">" + carouselLinkIndex + "</a>" : carouselLinks += "<a rel=\"" + carouselLinkIndex + "\"  title=\"97站长网" + carouselLinkIndex + " \" class=\"link" + carouselLinkIndex + "\" href=\"javascript:void(0)\" >" + carouselLinkIndex + "</a>";
                carouselLinkIndex++;
            }
            $("#numbers", obj).html(carouselLinks);

            //Bind carousel controls
            $(".next", obj).click(function() {
                carouselPage(parseInt(carouselPosition + 1), false);
                lock();
            });
            
            $(".previous", obj).click(function() {
                carouselPage(parseInt(carouselPosition - 1), false);
                lock();
            });

            $("#numbers a", obj).click(function() {
                carouselPage($(this).attr("rel"), false);
                lock();
            });

            $(".pause", obj).click(function() {
                autoPause();
            });
            $(".play", obj).click(function() {
                autoPlay();
            });

            function lock() {
                locked = true;
            }

            function unLock() {
                locked = false;
            }


            function checkPreviousNext() {
                $("#numbers a", obj).removeClass("selected");
                $("#numbers .link" + carouselPosition, obj).addClass("selected");
                
				if(options.auto == false)
				{
					(carouselPosition == carouselTotal) ? $(".next", obj).hide() : $(".next", obj).show();
					(carouselPosition < 2) ? $(".previous", obj).hide() : $(".previous", obj).show();
				}
            }
			
			function adjust() {

                if (carouselPosition < 1) {
                    
                    $(".backgrounds", obj).css("margin-left", (-1 * ((carouselTotal - 1) * carouselwidth)));
                    $(".details", obj).css("margin-left", (-1 * ((carouselTotal - 1) * detailWidth)));
                    carouselPosition = carouselTotal;

                }
                if (carouselPosition > carouselTotal) {
                    
                    $(".backgrounds", obj).css("margin-left", 0);
                    $(".details", obj).css("margin-left", 0);
                    carouselPosition = 1;
                }

            }

            function carouselPage(x, y) {

                if (locked != true) {

                    //console.log("New page: " + x);
                    carouselPosition = parseFloat(x);
                    //Cancel timer if manual click
                    if (y == false) autoPause();

                    var newPage = (x * carouselwidth) - carouselwidth;
                    var newPageDetail = (x * detailWidth) - detailWidth;

                    if (newPage != 0) {
                        newPage = newPage * -1;
                        newPageDetail = newPageDetail * -1;
                    }

                    $(".backgrounds", obj).animate({
                        marginLeft: newPage
                    }, {
                        "duration": options.durationCarousel, "easing": options.easingCarousel,

                        complete: function() {

                            //Now animate the details
							$(".details", obj).animate({
								marginLeft: newPageDetail
							}, {
								"duration": options.durationDetails, "easing": options.easingDetails,
								complete: function() {
									//alert("finished details");
									adjust();
									checkPreviousNext();
									unLock();
								}

							});
                            //adjust();
							//checkPreviousNext();
                            //unLock();
                        }
                    });
					
					
					
                }
                

            }

            function autoPause() {
                $(".pause", obj).hide();
                $(".play", obj).show();
                $("body").stopTime("autoScroll");
            }

            function autoPlay() {
                $(".pause", obj).show();
                $(".play", obj).hide();
                $("body").everyTime(options.autoDelay, "autoScroll", function() {
                    carouselPage(carouselPosition + 1, true);
                    lock();
                });
            }

            if (options.auto == true) {
                autoPlay();
            }

        });

    };
})(jQuery);// JavaScript Document