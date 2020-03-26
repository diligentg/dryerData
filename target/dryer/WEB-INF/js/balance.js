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
        url: 'http://localhost:8080/dryer/balances/selectAll' //数据接口
        ,
        title: '用户表',
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
                title: 'id',
            }, {
                field: 'uid',
                title: '姓名',
            },{
                field: 'remain',
                title: '余额',
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
                    title:"立即充值",
                    area:["60%","60%"],
                    content:"../templates/addBalance.html"
                })
                break;
            case 'update':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else if (data.length > 1) {
                    layer.msg('只能同时编辑一个');
                } else {

                    layer.open({
                        type:2,
                        title:"修改个人信息",
                        area:["60%","60%"],
                        content:"../templates/updateUsers.html"
                    })
                }
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请至少选择一行');
                } else {
                    layer.confirm("确认删除？", function () {
                        $.ajax({
                            type: "post",
                            url: "http://localhost:8080/dryer/users/del",
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


    //表单上传
    $("#sub").on('click',function () {
        $.ajax({
            type:"post",
            url:"http://localhost:8080/dryer/users/add",
            data:$("#biaodan").serialize(),//拿表单中input值
            dataType:"json",
            success:function (res) {
                layer.msg(res.message);
                console.log(res.message)
            }
        })
    })
})