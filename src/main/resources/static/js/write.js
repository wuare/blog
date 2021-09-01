/**
 * Created by wwxinjie on 2016-09-19.
 */
$(function(){
    $(".tag-list").hide();

    //位常用标签选择绑定click事件
    $("#tag-a").bind("click",function(){
        if(!$(".tag-list").is(":hidden")){
            $(".tag-list").hide();
        }else{
            $(".tag-list").show();
        }
    });

    //添加标签
    $(".tag-list a").click(function(){
        var text = "<i class='icon icon-cancel-circle'>" + $(this).text() + ", " + "</i>";
        $(".tag-display").append(text);
        $("#blogTag").val($(".tag-display").text())

        $(".tag-display i").click(function(){
            $(this).remove()
            $("#blogTag").val($(".tag-display").text())
        });
    });

    $("#tagButton").click(function(){
        //var len = $("#tagName").val().length;
        //if(len<=3){
        //    return true;
        //}else{
        //    alert("3");
        //    return false;
        //}
    })

});

