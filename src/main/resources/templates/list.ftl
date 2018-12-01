<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
    <meta charset="UTF-8">
    <title>相关文章</title>
    <link type="text/css" rel="stylesheet" href="/main.css"/>
</head>
<body>
    <div class="list-view">
    <#list article_list as article>
        <div class="article-item"><a href="/article/${article.uuid}">${article.name}</a></div>
    </#list>
    </div>
</body>