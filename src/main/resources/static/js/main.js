//判断主题
switch (getCookie("theme")) {
	case 'nasa':
		$("html").css({
			"--c": "#e979ee",
			"--cLogo": "#eb2a21",
			"--cFont": "#9bc0d5",
			"--cBg": "#c0fcdc"
		})
		break
	case 'ferrair':
		$("html").css({
			"--c": "#0ba046",
			"--cLogo": "#bf0e2b",
			"--cFont": "#000000",
			"--cBg": "#dbf5c4"
		})
		break
	case 'google':
		$("html").css({
			"--c": "#f4c20d",
			"--cLogo": "#4885ef",
			"--cFont": "#db3237",
			"--cBg": "#ecf8cf"
		})
		break
}

console.log(getCookie("theme"))
//换主题函数
$(".nasa").click(function() {
	document.cookie = "theme=" + "nasa";
	$("html").css({
		"--c": "#e37ae7",
		"--cLogo": "#eb2a21",
		"--cFont": "#88b9d5",
		"--cBg": "#f6e8fa"
	})
})
$(".ferrair").click(function() {
	document.cookie = "theme=" + "ferrair";
	$("html").css({
		"--c": "#0ba046",
		"--cLogo": "#bf0e2b",
		"--cFont": "#000000",
	// 	背景一起换了
		"--cBg": "#f5edcf"

	})
})
$(".google").click(function() {
	document.cookie = "theme=" + "google";
	$("html").css({
		"--c": "#f4c20d",
		"--cLogo": "#4885ef",
		"--cFont": "#db3237",
		"--cBg": "#cefcdb"
	})
})
//调整header的隐藏
function func() {
	var scroH = $(document).scrollTop() //滚动高度
	if (scroH < 150) { //距离顶部大于100px时
		$("header").slideDown("fast")
	}
	if (scroH > 150) { //距离顶部大于100px时
			$("header").slideUp("fast")
	}
}
//防抖函数
function debounce(func, wait) {
	let timeout
	return function() {
		if (timeout) clearTimeout(timeout)
		timeout = setTimeout(function() {
			func.apply(this)
		}, wait)
	}
}
//监听页面滚动
$(document).scroll(
		debounce(func, 50)
)
