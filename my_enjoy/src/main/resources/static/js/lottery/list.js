layui.config({
    base: "/static/js/"
}).use(['form', 'layer', 'jquery', 'laypage', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        $ = layui.jquery;
var body="body";
    //加载页面数据
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    $.ajax({
        url: "/lottery/list.json",
        type: "get",
        dataType: "json",
        success: function (data) {
            //执行加载数据的方法
            linksList(data);
            layer.close(index);
        }
    });


    //添加友情链接
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加彩种",
            type: 2,
            content: "/lottery/add.html",
            success: function (layero, index) {
                // console.log(layero);
                // console.log(index);
                // layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                //     tips: 3
                // });
            }
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });


    //操作
    $(body).on("click", ".links_edit", function () {  //编辑
        var _this = $(this);
        var id = _this.closest("tr").prop("id");
        window.location = "/lottery/update.html?id=" + id;
        // layer.alert('您点击了友情链接编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'友链编辑'});
    });

    $(body).on("click", ".links_del", function () {  //删除
        var _this = $(this);
        var id = _this.closest("tr").prop("id");
        if (id == null || id === '') {
            top.layer.msg("删除失败，获取ID信息失败");
        } else {
            layer.confirm('确定删除此信息？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    type: "POST",
                    url: "/lottery/delete.json",
                    data: {"id": id},
                    dataType: "json",
                    success: function (data) {
                        if (data.success) {
                            top.layer.close(index);
                            top.layer.msg("删除成功！");
                            layer.closeAll("iframe");
                            parent.location.reload();
                        } else {
                            top.layer.msg("删除失败[" + data.message + "]!");
                        }

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        top.layer.msg(XMLHttpRequest['responseText']);
                    }
                });
                layer.close(index);
            });
        }

    });

    function linksList(that) {
        var getTpl = list_tpl.innerHTML;
        laytpl(getTpl).render(that, function (html) {
            $("#t_body").html(html);
        });
        form.render();

    }
});
