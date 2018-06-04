function search() {
    var book_name = document.getElementById("searchTxt");

    var data = {};
    data["bookName"] = book_name.value;

    $.ajax({
        url:"search",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (response) {
            console.log(response);
            $("#postTable").html("<tr style=\"font-weight: bold\"><td>书名</td><td>作者</td><td>出版社</td><td>借阅状态</td><td>一键借阅</td></tr>");
            $.each(response, function (i, item) {
                var isrent;
                if(item.isrent == 0){
                    isrent = "在架上";
                    $("#postTable").append(
                        '<tr><td>'+item.bookName+'</td><td>'+item.bookWriter+'</td><td>'+item.Publisher+'</td><td>'+isrent+'</td><td><a class="btn btn-primary btn-xs" href="#"  role="button" id="'+item.book_id+'" onclick="borrowBook(this)">借阅</a></td></tr>'
                    );
                }else{
                    isrent = "已借出";
                    $("#postTable").append(
                        '<tr><td>' + item.bookName + '</td><td>' + item.bookWriter + '</td><td>' + item.Publisher + '</td><td>' + isrent + '</td><td><a class="btn btn-primary btn-xs" href="#" disabled="true" role="button" id="'+item.book_id+'">借阅</a></td></tr>'
                    );
                }
            })
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function borrowBook(obj) {
    var data = {};
    alert(obj.id.toString());
    data["id"] = obj.id.toString();
    //confirm() 方法用于显示一个带有指定消息和 OK 及取消按钮的对话框。
    if(confirm("请确定是否借阅这本书?")){
        $.ajax({
            url:"borrowBook",
            type:"POST",
            data:data,
            dataType:"JSON",
            success:function (response) {
                if(response === true){
                    alert("成功借阅！即日送达！");
                    setTimeout('window.location.href="Mybook.html"',1000);
                }
            },
            error:function (xhr, msg, e) {
                console.log(e);
            }
        });
    }
}