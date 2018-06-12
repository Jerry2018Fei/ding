layui.config({
	base : "/static/js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		// laypage = layui.laypage,
		// layedit = layui.layedit,
		// laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	// var editIndex = layedit.build('links_content');
 	var addLinksArray = [],lotteryConfig;
 	form.on("submit(lotteryConfig)",function(data){

        var lotteryConfig={};
        lotteryConfig.name=$("#name").val();
        lotteryConfig.nickName=$("#nickName").val();
        lotteryConfig.keyRule=$("#rowKeyRule").val();
        lotteryConfig.isSplit=$("#isSplit").val();
        lotteryConfig.frontSection=$("#frontSection").val();
        lotteryConfig.frontSectionRange=$("#frontSection_min").val()+";"+$("#frontSection_max").val();
        lotteryConfig.frontSectionRepeatable=$("#frontSectionRepeatable").val();
        lotteryConfig.backArea=$("#backArea").val();
        lotteryConfig.backAreaRange=$("#backArea_min").val()+";"+$("#backArea_max").val();
        lotteryConfig.backAreaRepeatable=$("#backAreaRepeatable").val();
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
