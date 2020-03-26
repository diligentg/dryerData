layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function () {
    var laydate = layui.laydate //日期
        ,
        laypage = layui.laypage //分页
        ,
        layer = layui.layer //弹层
        ,
        table = layui.table //表格
        ,
        carousel = layui.carousel //轮播
        ,
        upload = layui.upload //上传
        ,
        element = layui.element //元素操作
        ,
        slider = layui.slider //滑块
        ,
        $ = layui.jquery
    table.render({
        elem: '#demo',
        height: 420,
        url: 'http://localhost:8080/dryer/fixs/selectAll' //数据接口
        ,
        title: '报修表',
        page: true //开启分页
        ,
        toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,
        totalRow: true //开启合计行
        ,
        parseData: function (res) {
            return {
                "code": res.status,
                "msg": res.message,
                "total": res.total,
                "data": res.item,
            }
        },
        cols: [
            [ //表头
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                field: 'id',
                title: 'ID',
            }, {
                field: 'uname',
                title: '姓名',
            },{
                field: 'dormi',
                title: '寝室号',
            }, {
                field: 'num',
                title: '电话',
            }, {
                field: 'type',
                title: '类型',
            }, {
                field: 'msg',
                title: '故障描述',
            }, {
                field: 'pid',
                title: '图片',
                templet: function (dt) {
                    var s = dt.pid;
                    var img = "<img src='../" + s +
                        "' style='height: 150px;width: 150px;'/>"
                    return img;
                }
            }, {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });
    //监听头工具栏事件
    table.on('toolbar(test)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id)
            , data = checkStatus.data; //获取选中的数据
        var arr = new Array();
        for (var d in data) {
            arr[d] = data[d].id;
        }
        switch (obj.event) {
            case 'add':
                layer.open({
                    type:2,
                    title:"完善个人信息",
                    area:["60%","60%"],
                    content:"../templates/addFix.html"
                })

                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {

                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请至少选择一行');
                } else {
                    layer.confirm("确认删除？", function () {
                        $.ajax({
                            type: "post",
                            url: "http://localhost:8080/dryer/fixs/del",
                            traditional: true,
                            data: {
                                "id": arr
                            },
                            dataType: "json",
                            success: function (res) {
                                if (res.message == "success") {
                                    layer.msg(res.message)
                                } else {
                                    layer.msg(res.message)
                                }
                            }
                        })
                    })
                }

                break;
        }

    });
    //设置实现文件上传
    upload.render({
        elem: '#upload1'
        ,url: 'http://localhost:8080/dryer/fixs/up'
        ,accept: 'img'
        ,auto:false
        ,bindAction:'#uu'
        ,filed:'file'
        ,done: function(res){
            //从后台获取我们的文件名称
            //将名称放在input框中
            $("#audio_url").val(res.message)
            console.log(res.message)
        }
    });
    //表单上传
    $("#sub").on('click',function () {
        $.ajax({
            type:"post",
            url:"http://localhost:8080/dryer/fixs/add",
            data:$("#form").serialize(),//拿表单中input值
            dataType:"json",
            success:function (res) {
                layer.msg(res.message);
                console.log(res.message)
            }
        })
    })
})