var IndexClosure = (function() {

    var user_id = $('#user_id');
    var user_password = $('#user_password');

    function bind() {
        $('#submit_btn').click(function(e){
            e.preventDefault();
            if (valid()) {
                ajaxFunc('POST', '/router/login', data());
            } else {
                return false;
            }
        });
    }

    function data() {
        return {
            'user_id' : user_id.val(),
            'user_password' : user_password.val()
        }
    }

    function valid() {
        if (!user_id.val()) {
            alert("id를 입력해주세요.");
            user_id.focus();
            return false;
        }

        if (!user_password.val()) {
            alert('password를 입력해주세요.');
            user_password.focus();
            return false;
        }
        return true;
    }

    function ajaxFunc(method, url, data) {
        $.ajax({
           type:method,
           contentType:"application/json",
           url:url,
           data: JSON.stringify(data),
           dataType:'json',
           cahche:false,
           success:function(result) {
              alert(result["message"]);
              if (result["result"] === "success") {
                location.href = "/page/products";
              } else {
                user_password.val("");
                user_password.focus();
              }
           },
           error:function(error) {
              console.log(error);
           }
        });
    }

    return {
        bind : bind
    }
})();