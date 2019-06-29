window.onload=function(){
    var oDiv=document.getElementsByClassName('roll')[0];
    var oUl=oDiv.getElementsByTagName('ul')[0];
    var aLi=oUl.getElementsByTagName('li');

    var now=0;
    for(var i=0;i<aLi.length;i++)
    {
        aLi[i].index=i;
    }

    function next(){
        now++;
        if(now==aLi.length)
        {
            now=0;
        }
        startMove(oUl,{top:-26*now})
    }
    //设置广播滚动时间
    var timer=setInterval(next,3000);
    oDiv.onmouseover=function(){
        clearInterval(timer);
    };
    oDiv.onmouseout=function(){
        timer=setInterval(next,3000);
    }
};
//填充热门文章
function putInHotArticle(data)
{
    var bloglist = $('.blog-list');
    $.each(data, function (index, obj) {
            var center = $(
                '<li>'+
                '<a  href="' + obj['thisArticleUrl'] + '">' + obj['articleTitle'] + '</a>' +
                '</li>'
            )
            bloglist.append(center);
    })
}

//填充文章
function putInArticle(data) {
    var articles = $('.articles');
    $.each(data, function (index, obj) {
        if(index != (data.length) - 1){
            var center = $('<div class="am-slider-desc">' +
                '<header class="article-header">' +
                '<h1 itemprop="name">' +
                '<a class="article-title" href="' + obj['thisArticleUrl'] + '" target="_blank">' + obj['articleTitle'] + '</a>' +
                '</h1>' +
                '</header>' +
                '<div class="article-entry">' +
                obj['articleTabloid'] +
                '</div>' +
                '<div class="read-all">' +
                '<a href="' + obj['thisArticleUrl'] + '" target="_blank">阅读全文 <i class="am-icon-angle-double-right"></i></a>' +
                '</div>' +
                '<hr class="style-two">' +
                '<div class="article-tags">' +

                '</div>' +
                '</div>');
            articles.append(center);
           /* var articleTags = $('.article-tags');
            for(var i=0;i<obj['articleTags'].length;i++){
                var articleTag = $('<i class="am-icon-tag"><a class="tag" href="/tags?tag=' + obj['articleTags'][i] + '"> ' + obj['articleTags'][i] + '</a></i>');
                articleTags.eq(index).append(articleTag);
            }*/
            // var likes = $('<span class="likes"><i class="am-icon-heart"> ' + obj['likes'] + '个喜欢</i></span>');
            // articleTags.eq(index).append(likes);
        }
    })

}
function ajaxHotArt() {
    $.ajax(
        {
            type:'get',
            url:'/gethotAtricle',
            dataType: 'json',
            data: {

            },
            success:function (data) {
                putInHotArticle(data);
                scrollTo(0,0);//回到顶部
            },
            error: function () {
                alert("获得热门文章失败！");
            }
        }
    )
    
}

//首页文章分页请求
function ajaxFirst(currentPage) {
    //加载时请求
    $.ajax({
        type: 'POST',
        url: '/myArticles',
        dataType: 'json',
        data: {
            rows:"10",
            pageNum:currentPage
        },
        success: function (data) {
            //放入数据
            putInArticle(data);
            scrollTo(0,0);//回到顶部

            //分页
            $("#pagination").paging({
                rows:data[data.length-1]['pageSize'],//每页显示条数
                pageNum:data[data.length-1]['pageNum'],//当前所在页码
                pages:data[data.length-1]['pages'],//总页数
                total:data[data.length-1]['total'],//总记录数
                callback:function(currentPage){
                    ajaxFirst(currentPage);
                }
            });
        },
        error: function () {
            alert("获得文章信息失败！");
        }
    });
}

ajaxFirst(1);
ajaxHotArt();