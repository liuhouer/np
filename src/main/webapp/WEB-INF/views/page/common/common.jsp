<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/qinco.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrapValidator.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/main2.css">
<link rel="stylesheet" href="/css/player.css">
<input  type="hidden" id="J_tab_name" value="${ tabs}"/>

<!--bruce music player loading -->
<div id="QPlayer">
		<div id="pContent">
			<div id="player">
				<span class="cover"></span>
				<div class="ctrl">
					<div class="musicTag marquee">
						<strong>Title</strong> <span> - </span> <span class="artist">Artist</span>
					</div>
					<div class="clearfix progress1">
						<div class="timer left">0:00</div>
						<div class="contr">
							<div class="rewind icon"></div>
							<div class="playback icon"></div>
							<div class="fastforward icon"></div>
						</div>
						<div class="right">
							<div class="liebiao icon"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="ssBtn">
				<div class="adf"></div>
			</div>
		</div>
		<ol id="playlist"></ol>
</div>






