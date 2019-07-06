

    var category = "";

    //填充标签文章信息
    function putIArticleByCategory(data) {
        var archiveswrap = $('.archives-wrap');
        archiveswrap.empty();
        var Taghead = $('<h1 class="Taghead">'+data['category']+'</h1>'
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
                '<div class="article-category tagcloud">' +
                '<ul class="article-tag-list">'+
                '<li class="article-tag-list-item"><a class="article-tag-list-link color0 color6 color3" href="/category?category=' +
                    obj['articleCategories'] + '"> ' + obj['articleCategories'] + '</a></li>'+
                ' </ul>' +
                '</div>' +
                '</div>' +
                '<div class="clearfix"></div>' +
                '</header>' +
                '</div>' +
                '</article>'
            ));
            archives.append(acticle);

        })
    }
    $.ajax({
        type: 'HEAD', // 获取头信息，type=HEAD即可
        url : window.location.href,
        async:false,
        success:function (data, status, xhr) {
            category = xhr.getResponseHeader("category");
        }
    });

    function ajaxFirst(currentPage,category) {
        $.ajax({
            type:'GET',
            url:'/getCategoryArticle',
            dataType:'json',
            data:{
                category:category,
                rows:"10",
                pageNum:currentPage
            },
            success:function (data) {
                putIArticleByCategory(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#pagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        ajaxFirst(currentPage, category1);
                    }
                });

            },
            error:function () {
                alert("获取分类文章失败");
            }
        });
    }
    ajaxFirst(1,category);

 /*   $.ajax({
        type:'GET',
        url:'/findCategoriesNameAndArticleNum',
        dataType:'json',
        data:{
        },
        success:function (data) {
            var categories = $('.categories');
            categories.empty();
            categories.append($('<div class="categories-title">' +
                '<h3 style="font-size: 20px">Categories</h3>' +
                '</div>'));
            var categoriesComment = $('<div class="categories-comment am-animation-slide-top"></div>');
            $.each(data['result'], function (index, obj) {
                categoriesComment.append($('<div class="category">' +
                    '<span><a class="categoryName">' + obj['categoryName'] + '</a><span class="categoryNum">(' + obj['categoryArticleNum'] + ')</span></span>' +
                    '</div>'));
            });
            categories.append(categoriesComment);
            $('.categoryName').click(function () {
                var $this = $(this);
                var categoryName = $this.html();
                ajaxFirst(1,categoryName);
            })
        },
        error:function () {
            alert("获取分类信息失败");
        }
    });*/