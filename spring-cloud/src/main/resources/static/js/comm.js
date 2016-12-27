function scrollTop(){return $("html, body").animate({scrollTop: 0},"slow"),!1}

//js生成随机数    n表示生成几位的随机数
function jsRand(n) {
	var jschars = ['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
    var res = "";
    for(var i = 0; i < n ; i ++) {
        var id = Math.ceil(Math.random()*61);
        res += jschars[id];
    }
    return res;
}

//获取窗口长宽
function resizeWindow()
{
	var winW = $(window).width();
	if(winW < 500) {
		$("#nav li a").css("padding","10px 10px");
	}else{
		$("#nav li a").css("padding","10px 30px");
	}
}