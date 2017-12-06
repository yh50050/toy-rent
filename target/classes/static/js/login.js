var baseUrl = window.location.href;
/** 控制管理员或者营业员登录 */
$(function() {

	$("#login1").click(
			function() {

				var uname = $.trim($("#uname").val());
				var passwd = $.trim($("#passwd").val());

				if (checkInput(uname, passwd)) {
					$.ajax({
						type : "POST",
						url : "employee/login",
						data : {
							"uname" : uname,
							"passwd" : passwd
						},
						dataType : "json",
						success : function(msg) {
							if ("success" == msg.message) {
								window.location.href = baseUrl.substring(0,
										baseUrl.indexOf(""))
										+ "/index";
							}
							if ("fail" == msg.message) {
								layui.use('layer', function() {
									var layer = layui.layer;
									layer.msg('用户名或密码错误');
								});
							}
							if ("error" == msg.message) {
								layui.use('layer', function() {
									var layer = layui.layer;
									layer.msg('用户名或密码不能为空');
								});
							}
							if ("no" == msg.message) {
								layui.use('layer', function() {
									var layer = layui.layer;
									layer.msg('该账号已被停封');
								});
							}
						}
					});
				}
			})

	$("#login2").click(
			function() {

				var uname = $.trim($("#uname").val());
				var passwd = $.trim($("#passwd").val());

				if (checkInput(uname, passwd)) {
					$.ajax({
						type : "POST",
						url : "admin/login",
						data : {
							"uname" : uname,
							"passwd" : passwd
						},
						dataType : "json",
						success : function(msg) {
							if ("success" == msg.message) {
								window.location.href = baseUrl.substring(0,
										baseUrl.indexOf(""))
										+ "/index";
							}
							if ("fail" == msg.message) {
								layui.use('layer', function() {
									var layer = layui.layer;
									layer.msg('用户名或密码错误');
								});
							}
							if ("error" == msg.message) {
								layui.use('layer', function() {
									var layer = layui.layer;
									layer.msg('用户名或密码不能为空');
								});
							}
						}
					});
				}
			})
})

function checkInput(uname, passwd) {

	if ("" == uname || "" == passwd) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('用户名或密码不能为空');
		});
		return false;
	}
	return true;
}
