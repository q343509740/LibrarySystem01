function get_data(){
    var username = document.getElementById('username');
    var password = document.getElementById('password');
    var checkor = document.getElementById('check');
    var data = {};
    data['username'] = username.value;
    data['password'] = password.value;
    if(!checkor.checked){
        username.text("");
        password.text("");
    }
    return data;
}

function login_submit() {
    var data = get_data();

    $.ajax({
        url:"login",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (response) {
            if(response === true){
                setTimeout('window.location.href="indexpage.html"');
            }else{
                alert("账号或密码错误");
            }
        },
        error:function (xhr, msg, e) {
           alert("error!");
        }
    });
}

function register_data() {
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var repassword = document.getElementById("repassword");
    var email = document.getElementById("email");

    if(password.value != repassword.value){
        alert("两次密码不一致!");
        return;
    }
    if(username.value == ""){
        alert("信息不能为空!");
        return;
    }

    var data = {};
    data["username"] = username.value;
    data["password"] = password.value;
    data["repassword"] = repassword.value;
    data["email"] = email.value;

    return data;
}

function register_submit(){
    var data = register_data();

    $.ajax({
        url:"register",
        type:"POST",
        data:data,
        dataType:"JSON",
        success:function (response) {
            if(response === true){
                alert("注册成功,3秒后跳转登录页面!");
                setTimeout('window.location.href="index.html"',3000);
            }else{
                alert("账号已存在,请重新输入!")
            }
        },
        error: function (xhr, msg, e) {
            alert("error!");
        }
    });
}

function online() {
    $.ajax({
        url:"online",
        type:"GET",
        success:function (response) {
            if(response != null){
                $("#UserID").innerHTML(response.toString() + '<b class="caret"></b>');
            }
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function updatePwd() {
    var password1 = document.getElementById("password1");
    var password2 = document.getElementById("password2");
    if(password1.value !== password2.value){
        alert("两次密码不一致,请输入输入!");
        password1.text = "";
        password2.text = "";

    }else{
        var data = {};
        data["password"] = password2.value;
        $.ajax({
            url:"updatePwd",
            type:"POST",
            data:data,
            dataType:"JSON",
            success:function (response) {
                if(response == true){
                    alert("修改密码成功,请重新登录!");
                    setTimeout('window.location.href="index.html"');
                }
            }
        });
    }
}

function logout() {
    $.ajax({
        url:"logout",
        type:"GET",
        dataType:"JSON",
        success:function (response) {
            if(response == true){
                alert("退出成功");
                setTimeout('window.location.href="index.html"');
            }else{
                alert("退出失败");
            }
        },
        error:function (xhr, msg, e) {
            alert("error");
        }
    });
}