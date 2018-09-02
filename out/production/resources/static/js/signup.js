var SignupClosure = (function() {

    var user_id = $('#user_id');
    var user_password = $('#user_password');
    var user_confirm_password = $('#user_confirm_password');
    var user_nickname = $('#user_nickname');
    var user_email = $('#user_email');
    var user_name = $('#user_name');
    function bind() {
        $('#submit_btn').click(function(e){
            e.preventDefault();
            if (valid()) {
                function success(result) {
                   alert(result["message"]);
                   if (result["result"] === "success") {
                       location.href = "/";
                   }
                }
               ajaxFunc("POST", "/router/signup", data(), success);
            }
        });

        $("#user_id").keyup(function() {

        });
    }

    function data() {
        return {
            "user_id" : user_id.val(),
            "user_password" : user_password.val(),
            "user_confirm_password" : user_confirm_password.val(),
            "user_nickname" : user_nickname.val(),
            "user_email" : user_email.val(),
            "user_name" : user_name.val()

        }
    }

    function valid() {
        if (!user_id.val()) {
            alert("id를 입력해 주세요.");
            user_id.focus();
            return false;
        }

        if (!user_password.val()) {
            alert("password를 입력해주세요.");
            user_password.focus();
            return false;
        }

        if (!user_confirm_password.val()) {
            alert("password를 재입력해주세요.");
            user_confirm_password.focus();
            return false;
        }

        if (user_password.val() !== user_confirm_password.val()) {
            alert("password와 password 확인이 일치하지 않습니다.");
            user_confirm_password.focus();
            return false;
        }

        if (!user_email.val()) {
            alert("email을 입력해주세요");
            user_email.focus();
            return false;
        }

        if (!user_name.val()) {
            alert("name을 입력해주세요.");
            user_name.focus();
            return false;
        }
        return true;
    }

    function ajaxFunc(method, url, data, success) {
            $.ajax({
               type:method,
               contentType:"application/json",
               url:url,
               data: JSON.stringify(data),
               dataType:'json',
               cahche:false,
               success:success,
               error:function(error) {
                  console.log(error);
               }
            });
    }

    return {
        bind : bind
    }
})();