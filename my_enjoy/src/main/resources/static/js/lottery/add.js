layui.config({
	base : "/static/js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,

		$ = layui.jquery;


 	form.on("submit(*)",function(data){

        var lotteryConfig={};
        lotteryConfig.name=$("#name").val();
        lotteryConfig.frontSection=$("#frontSection").val();
        lotteryConfig.frontSectionMax=$("#frontSectionMax").val();
        lotteryConfig.frontSectionNumber=$("#frontSectionNum").val();
        lotteryConfig.frontSectionRepeatable=$("input[name='frontSectionRepeatable']:checked").val();
        lotteryConfig.backArea=$("#backArea").val();
        lotteryConfig.backAreaMax=$("#backAreaMax").val();
        lotteryConfig.backAreaRepeatable=$("input[name='backAreaRepeatable']:checked").val();
        lotteryConfig.backAreaNumber=$("#backAreaNum").val();
        console.log(JSON.stringify(lotteryConfig));
        //弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            $.ajax({
                type: "POST",
                url: "/lottery/add.json",
                data: JSON.stringify(lotteryConfig),
                // data: {"lotteryConfig":JSON.stringify(lotteryConfig)},
                dataType: "json",
                headers : {
                    'Content-Type' : 'application/json;charset=utf-8'
                },
                success: function (data) {
                    console.log(data);
                    if(data.success){
                        top.layer.close(index);
                        top.layer.msg("添加成功！");
                        layer.closeAll("iframe");
                        parent.location.reload();
                    }else {
                        top.layer.msg("添加失败["+data.message+"]!");
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    top.layer.msg(XMLHttpRequest['responseText']);
                }
            });

        },1);

 		return false;
 	})
	
})
