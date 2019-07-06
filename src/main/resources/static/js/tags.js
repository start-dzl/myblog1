
    var tag="";

    /*//添加所有标签
    function putInAllTags(data) {
        var allTags = $('.allTags');
        allTags.empty();
        allTags.append($('<div class="allTagsTitle">' +
            '<h2 style="font-size: 20px">Tags</h2>' +
            '</div>'));
        allTags.append($('<div class="allTagsNum">' +
            '目前共计 <span class="num" style="font-size: 20px">' + data['tagsNum'] + '</span> 个标签' +
            '</div>'));
        var allTagsCloud = $('<div class="allTagsCloud categories-comment am-animation-slide-top"></div>');
        $.each(data['result'], function (index, obj) {
            allTagsCloud.append($('<a href="/tags?tag=' + obj['tagName'] + '" style="font-size:' + obj['tagSize'] + 'px">' + obj['tagName'] + '</a>'));
        });
        allTags.append(allTagsCloud);
    }*/

    //添加该标签的所有文章信息
    function putInTagArticleInfo(data) {
        var archiveswrap = $('.archives-wrap');
        archiveswrap.empty();
        var Taghead = $('<h1 class="Taghead">'+data['tag']+'</h1>'
        +'<div class="archives"></div>'
        );
        archiveswrap.append(Taghead);
        var archives = $('.archives');
        $.each(data['result'],function (index,obj){
            var acticle=($(
               '<article class="archive-article ">'+
                '<div>'+
                '<header>'+
                '<div class="article-meta">'+
                '<a class="archive-article-date"><time itemprop="datePublished">'+obj['publishDate']+'</time>' +
                '</a>' +
                '</div>'+
                '<h1 >' +
                '<a target="_blank" class="article-title" href="' + obj['thisArticleUrl']+ '">'+obj['articleTitle']+'</a>' +
                '</h1>'+
                '<div class="article-info info-on-archive">' +
                '<div class="article-tag tagcloud">' +
                '<ul class="article-tag-list">'+
                ' </ul>' +
                '</div>' +
                '</div>' +
                '<div class="clearfix"></div>' +
                '</header>' +
                '</div>' +
                '</article>'
            ));
            archives.append(acticle);
            var articletaglist = $('.article-tag-list');
            for(var i=0;i<obj['articleTags'].length;i++){
                var articleTag = $('<li class="article-tag-list-item"><a class="article-tag-list-link color0 color6 color3" href="/tags?tag=' + obj['articleTags'][i] + '"> ' + obj['articleTags'][i] + '</a></li>');
                articletaglist.eq(index).append(articleTag);
            }
        })
    }

    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url : window.location.href,
        async:false,
        success:function (data, status, xhr) {
            tag = xhr.getResponseHeader("tag");
        }
    });

    //加载tags页时请求
    function ajaxFirst(currentPage) {

        $.ajax({
            type:'post',
            url:'/getTagArticle',
            dataType:'json',
            data:{
                tag:tag,
                rows:"5",
                pageNum:currentPage
            },
            success:function (data) {
                if(data['status'] == 200){
                    putInAllTags(data);
                } else {
                    putInTagArticleInfo(data);
                    scrollTo(0,0);//回到顶部

                    //分页
                    $("#pagination").paging({
                        rows:data['pageInfo']['pageSize'],//每页显示条数
                        pageNum:data['pageInfo']['pageNum'],//当前所在页码
                        pages:data['pageInfo']['pages'],//总页数
                        total:data['pageInfo']['total'],//总记录数
                        callback:function(currentPage){
                            ajaxFirst(currentPage);
                        }
                    });
                }
            },
            error:function () {
                alert("获取标签文章失败");
            }
        });
    }
    ajaxFirst(1);