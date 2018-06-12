<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>文章添加--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="/static/css/font_eolqem241z66flxr.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <br>
    <div class="layui-form-item">
        <label class="layui-form-label">彩种名称</label>
        <div class="layui-input-block">
            <input type="text" id="name" name="name" class="layui-input " lay-verify="required" placeholder="请输入彩种名称">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">Hbase Table 名称</label>
        <div class="layui-input-block">
            <input type="text" id="nickName" name="nickName" class="layui-input " lay-verify="required" placeholder="请输入Hbase Table 名称">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">主键规则</label>
        <div class="layui-input-block">
            <input type="radio" id="rowKeyRule" name="rowKeyRule" value="1" title="id" checked="checked">
            <input type="radio" id="rowKeyRule" name="rowKeyRule" value="2" title="uuid">
            <input type="radio" id="rowKeyRule" name="rowKeyRule" value="3" title="时间戳">
            <#--<input type="text" id="rowKeyRule" name="rowKeyRule" class="layui-input " lay-verify="required" placeholder="请输入主键规则">-->
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否分区</label>
        <div class="layui-input-block">
            <input type="radio" id="isSplit" name="isSplit" value="1" title="是" checked="checked">
            <input type="radio" id="isSplit" name="isSplit" value="0" title="否">
            <#--<input type="radio" name="sex" value="禁" title="禁用" disabled="">-->
            <#--<input type="text" id="isSplit" name="isSplit" class="layui-input " lay-verify="required" placeholder="请选择">-->
        </div>
    </div>
    <#--<div class="layui-form-item" pane="">-->
        <#--<label class="layui-form-label">单选框</label>-->
        <#--<div class="layui-input-block">-->
            <#--<input type="radio" name="sex" value="男" title="男" checked="">-->
            <#--<input type="radio" name="sex" value="女" title="女">-->
            <#--<input type="radio" name="sex" value="禁" title="禁用" disabled="">-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">前区名称</label>
        <div class="layui-input-block">
            <input type="text" id="frontSection" name="frontSection" value="红球" class="layui-input " lay-verify="required" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">前区范围</label>
        <div class="layui-input-block">
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="frontSection_min" name="frontSection_min" value="1" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="frontSection_max" name="frontSection_max" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
            <#--<input type="text" id="frontSectionRange" name="frontSectionRange" class="layui-input " lay-verify="required" placeholder="请输入">-->
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">前区可否重复</label>
        <div class="layui-input-block">
            <input type="radio" id="frontSectionRepeatable" name="frontSectionRepeatable" value="1" title="是" >
            <input type="radio" id="frontSectionRepeatable" name="frontSectionRepeatable" value="0" title="否" checked="checked">
            <#--<input type="text" id="frontSectionRepeatable" name="frontSectionRepeatable" class="layui-input " lay-verify="required" placeholder="请输入">-->
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">后区名称</label>
        <div class="layui-input-block">
            <input type="text" id="backArea" name="backArea" class="layui-input " value="蓝球" lay-verify="required" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">后区范围</label>
        <div class="layui-input-block">
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="backArea_min" name="backArea_min" value="1" placeholder="请输入" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="backArea_max" name="backArea_max" placeholder="请输入" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
            <#--<input type="text" id="backAreaRange" name="backAreaRange" class="layui-input " lay-verify="required" placeholder="请输入">-->
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">后区可否重复</label>
        <div class="layui-input-block">
            <input type="radio" id="backAreaRepeatable" name="backAreaRepeatable" value="1" title="是" checked="checked">
            <input type="radio" id="backAreaRepeatable" name="backAreaRepeatable" value="0" title="否">
            <#--<input type="text" id="backAreaRepeatable" name="backAreaRepeatable" class="layui-input " lay-verify="required" placeholder="请输入">-->
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="lotteryConfig">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/lottery/add.js"></script>
</body>
</html>