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
    var tableIns=table.render({
        elem: '#demo',
        height: 420,
        url: 'http://localhost:8080/dryer/users/selectAll' //数据接口
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
                title: 'ID',
            }, {
                field: 'user',
                title: '用户',
            },
            //     {
            //     field: 'password',
            //     title: '密码',
            // },
                {
                field: 'name',
                title: '姓名',
                edit:'text',
            }, {
                field: 'sex',
                title: '性别',
                edit:'text',
            }, {
                field: 'dormi',
                title: '寝室号',
                edit:'text',
            }, {
                field: 'pid',
                title: '头像',
                templet: function (dt) {
                    var s = dt.pid;
                    var img = "<img src='../" + s +
                        "' style='height: 150px;width: 150px;'/>"
                    return img;
                }
            },, {
                field: 'classmsg',
                title: '班级信息',
                edit:'text',
            }, {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });



    //点击搜索按钮，实现模糊查询
    $("#btn").on("click",function () {
        //按条件查询,执行表格数据重新加载（根据输入的条件模糊查找的数据）
        table.reload('demo', {
            url:"../users/selectByLike",
            where: { //设定异步数据接口的额外参数，任意设
                "name": $("#demoReload").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据
    })

    //监听单元格编辑
    //update
    table.on('edit(test)', function(obj){
        console.log(obj);
        var value = obj.value //得到修改后的值
            ,d = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        $.ajax({
            type:"post",
            url:"../users/update",
            data:{
                "value":value,
                "id":d.id,
                "field":field
            },
            dataType:"json",
            success:function (res) {
                layer.msg(res.message)
            }
        })
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
                    content:"../templates/addUsers.html"
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
                                content:"../templates/updateUsers.html",
                                done: function(res){
                                //从后台获取我们的文件名称
                                //将名称放在input框中
                                console.log(data)
                                console.log(res.message)
                                // $.ajax({
                                //     type:"post",
                                //     url:"http://localhost:8080/dryer/users/update",
                                //     data:{
                                //         id:data.id
                                //     },
                                //     dataType:"json",
                                //     success:function (res) {
                                //
                                //
                                //     }
                                // })
                        }
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

                                    layer.msg(res.message,function () {
                                        tableIns.reload()
                                    })

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
        ,url: 'http://localhost:8080/dryer/users/up'
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