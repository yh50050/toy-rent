var baseUrl = window.location.href;
			$(function() {
				var start = /*[[${message.data.startPage}]]*/
				var end = /*[[${message.data.endPage}]]*/
			    var page1 = /*[[${message.data.paging}]]*/
				var count = /*[[${message.data.pageCount}]]*/
				function showPaging(){
					$("#page-ul").append('<li><span id="previous" aria-label="Previous" style="cursor: pointer;"> <span aria-hidden="true">&laquo;</span> </span></li>');
					while (parseInt(end) >= parseInt(start))
					{
						$("#page-ul").append('<li><a href="#" >'+start+'</a></li>');
						if(start == page1)
							$("#page-ul li:last-child").addClass("active");
						start = start + 1;
					}
					$("#page-ul").append('<li><span id="next" aria-label="Next" style="cursor: pointer;"> <span aria-hidden="true">&raquo; </span></span></li>');
				}
				showPaging();
				
				$("#page-ul li a").click(function() {
					$("#page-ul li").removeClass("active")
					$(this).addClass("active")
				})
				/** 控制页码显示是否访问 */
				$("#page-ul li a").click(
						function() {
							var page = $(this).html();
							var name = ""
							var branch = ""
							window.location.href = baseUrl.substring(0, baseUrl
									.indexOf(""))
									+ "/employee/query-list?page=" + page;
						})
				/** 上一页 */
				$("#previous").click(
						 function() {
							if (page1 > 1){
								page1 = page1 - 1;
								window.location.href = baseUrl.substring(0, baseUrl
										.indexOf(""))
										+ "/employee/query-list?page=" + page1; 
							}
						})
				/** 下一页 */
				$("#next").click(function() {
						if(parseInt(count) > parseInt(page1)){
							page1 = page1 + 1;
							window.location.href = baseUrl.substring(0, baseUrl
									.indexOf(""))
									+ "/employee/query-list?page=" + page1; 
							}
						})
				/** 选择跳转第几页 */
				$("#page").change(function(){
					var page = $("#page").val();
					if(parseInt(page) > 0){
						if(parseInt(count) >= parseInt(page)){
							window.location.href = baseUrl.substring(0, baseUrl
									.indexOf(""))
									+ "/employee/query-list?page=" + page; 
						    }
					    }
				    })
				$("#search01").click(function(){
					var name = $("#searchname").val();
					alert(name)
					window.location.href = baseUrl.substring(0, baseUrl
							.indexOf(""))
							+ "/employee/query-list?name=" + name;
				})

				$("#li01").addClass("active menu-open");
				$("#li01 ul li:first").addClass("active");

			});