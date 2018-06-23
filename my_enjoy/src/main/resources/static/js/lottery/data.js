var t;
layui.config({
    base: "/static/js/"
}).use(['form', 'layer', 'jquery', 'layedit', 'laydate','laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laytpl=layui.laytpl,
        $ = layui.jquery;
    var body=$("body");
    var configId=$("#configId");

    form.on('select(test)', function(data){
        if(data.value!==''){
            $.ajax({
                url: "/lottery/getById.json",
                type: "get",
                dataType: "json",
                data:{"id":data.value},
                success: function (item) {
                    //执行加载数据的方法
                    var getTpl = demo.innerHTML;
                    laytpl(getTpl).render(item, function (html) {
                        $("#t_body").html(html);
                    });
                    form.render();
                    // layer.close(index);
                }
            });
        }

    });
    body.on("click",".get_btn",function () {
        // alert(123123);
        // console.log(configId.val());
        if(configId.val()===''){
            alert("请先选择彩种信息")
        }else {

        }
        $.ajax({
            url: "/lottery/data.json",
            type: "get",
            dataType: "json",
            data:{"id":configId.val()},
            success: function (item) {
                console.log(item);
                //执行加载数据的方法
                var getTpl = demo2.innerHTML;
                laytpl(getTpl).render(item, function (html) {
                    $("#t_body2").html(html);
                });
                var id=item.id;
                t = window.setInterval('getDataProcess("lottery_'+id+'")', 2000);
                // form.render();
                // layer.close(index);
            }
        });

    });
    body.on("click",".clear_btn",function () {
        $("#t_body2").html("<tr><td colspan='4'>暂无数据</td></tr>");
    });


});
function getDataProcess(id) {
    console.log(id);
    layui.use(["jquery"],function () {
        var $ = layui.jquery;
        $.ajax({
            url: "/lottery/dataProcess.json",
            type: "get",
            dataType: "json",
            data:{"key": id},
            success: function (d) {
                if (d.status) {
                    window.clearInterval(t);
                    $("#"+id+" td:eq(3)").html("完成");
                    $("#"+id+" td:eq(2)").html(d.result);
                } else {
                    $("#"+id+" td:eq(2)").html(d.result);
                }
            }
        });
    });


}