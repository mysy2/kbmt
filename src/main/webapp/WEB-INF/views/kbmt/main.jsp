<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" />
<title>BMT 챗봇도우미</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/chat.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</head>
<body>
	<div class="chat_wrap">
		<!-- chat_top -->
		<div class="chat_top">
			<h1><a href="#"> <img src="images/wiseichatlog.png" alt="챗봇도우미" /></a></h1>
		</div>
		<!--  //chat_top -->

		<!-- talk_wrap -->
		<div class="talk_wrap">
			<div class="bot_wrap clearfix">
				<div class="bot_img"><img src="images/icon_bot.png" alt="KOMIPO 챗봇" /></div>
				<div class="box_txt">
					안녕하세요.<strong>KOMIPO 챗봇</strong>입니다.
				</div>
			</div>
			<!-- <div class="user_wrap clearfix">
				<div class="user_txt">
					안녕하세요.
				</div>
				<div class="user_time">17:06</div>
			</div>
			<div class="bot_wrap clearfix">
				<div class="bot_img"><img src="images/icon_bot.png" alt="KOMIPO 챗봇"/></div>
				<div class="box_txt">
					중부발전 챗봇입니다^^ 질문이 있으시면 언제든지 입력해주세요.
					지금까지 학습한 내용으로 바로 답변드릴거에요.
				</div>
			</div>
			<div class="user_wrap clearfix">
				<div class="user_txt">
					공공구매 목표율이 궁금해요.
				</div>
				<div class="user_time">17:10</div>
			</div>
			<div class="bot_wrap clearfix">
				<div class="bot_img"><img src="images/icon_bot.png" alt="KOMIPO 챗봇"/></div>
				<div class="box_txt">
					다음 중 <strong>선택</strong>해주세요.
				</div>
			</div>
			<div class="user_wrap clearfix">
				<div class="user_txt">
					기술개발제품
				</div>
				<div class="user_time">17:15</div>
			</div>
			<div class="bot_wrap clearfix">
				<div class="bot_img"><img src="images/icon_bot.png" alt="KOMIPO 챗봇"/></div>
				<div class="box_txt">
					<strong>보령</strong>: 21% <strong>인천</strong>: 18% <strong>제주</strong>: 18%
					<strong>세종</strong>: 18% <strong>신보령</strong>: 18%
				</div>
			</div> -->
		</div>
		<!--// talk_wrap -->

		<!-- chat_bottom -->
		<form id="question" name="question" method="POST" action="javascript:question()">
			<div class="chat_bottom">
				<div class="chat_write_box">
					<input type="text" value="" id="query" placeholder="챗봇에게 문의해 보세요." autocomplete="off"  onkeypress="if(event.keyCode==13) {ichat_question(''); return false;}" />
					<a href="#" class="send_btn" onclick="ichat_question('');">전송</a>
				</div>
			</div>
		</form>	
		<!--// chat_bottom -->
	</div>
</body>
</html>