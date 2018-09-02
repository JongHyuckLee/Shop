var Products = (function() {

    function bind() {
        $(".products").click(function(e){
            e.preventDefault();
            var _this = $(this);
            console.log(_this);
            var id = _this.find("input").val();
            console.log(id);
            if (confirm("상품을 구매 하시겠습니까")) {
                ajaxFunc("POST", "/router/purchase", {"id" : id});
            }

        });
        $('#purchased_list').click(function(e) {
            e.preventDefault();
            var _this = $(this);
            var _id = _this.attr("data");
            var url = "/page/purchased_list";
            location.href = url;
        });
    }
    function ajaxFunc(url) {
        $.ajax({
           type:"GET",
           contentType:"application/json",
           url:url,
           data: JSON.stringify({}),
           dataType:'json',
           cahche:false,
           success:function(result) {
              console.log(result);
           },
           error:function(error) {
              console.log(error);
           }
        });
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
              var code = Number(result["result"]);
              alert(result["message"]);

              if(code === -1) {
                   location.href="/";
              } else {
                location.reload();
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