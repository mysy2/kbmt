/**
 * 
 */
var d = new Date();

var  question_top_html = "<div class=\"user_wrap clearfix\"><div class=\"user_txt\">";
var  question_bottom_html = "</div><div class=\"user_time\">"+d.getHours()+":"+d.getMinutes()+"</div></div>";

var answer_top_html = "<div class=\"bot_wrap clearfix\"><div class=\"bot_img\"><img src=\"images/icon_bot.png\" alt=\"KOMIPO 챗봇\"/></div><div class=\"box_txt\">";
var answer_bottom_html = "</div></div>";


function ichat_question(gubun) {	
	var query = $('#query').val();
	
	
	//질문
	var tempStr = "";
    tempStr += question_top_html;
    tempStr += query;
    tempStr += question_bottom_html;

    $(".talk_wrap").append(tempStr);
    $(".talk_wrap").scrollTop($('.talk_wrap')[0].scrollHeight);
    $('#talk_wrap').scrollTop(500);
    
    //답변
    var answerStr = "";
	answerStr += answer_top_html;
	answerStr += "질문에 부합되는 답변이 없습니다. 정확히 다시 문의 부탁드립니다.";
	answerStr += answer_bottom_html;	
    $(".talk_wrap").append(answerStr);
    $(".talk_wrap").scrollTop($('.talk_wrap')[0].scrollHeight);
    $('#talk_wrap').scrollTop(500);
    
    $('#query').val("");
}



