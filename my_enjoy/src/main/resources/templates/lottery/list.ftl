<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章列表--layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="/static/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="/static/css/news.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn">查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn add_btn" style="background-color:#5FB878">添加新种类</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel">批量删除</a>
		</div>

	</blockquote>
	<div class="layui-form links_list">
	  	<table class="layui-table">
		    <thead>
				<tr>
					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th >ID</th>
					<th >名称</th>
					<th >表名</th>
					<th>主键生成规则</th>
					<th>是否分区</th>
					<th>前区名称</th>
					<th>前区范围</th>
					<th>前区可否重复</th>
                    <th>后区名称</th>
                    <th>后区范围</th>
                    <th>后区可否重复</th>
                    <th>是否已经处理过</th>
					<th width="20%">操作</th>
				</tr> 
		    </thead>
		    <tbody class="links_content"></tbody>
		</table>
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="/static/layui/layui.js"></script>
	<script type="text/javascript" src="/static/js/lottery/list.js"></script>
</body>
</html>