<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
    <meta charset="UTF-8">
    <title>${article.name}</title>
    <script src="/jquery-3.3.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/main.css"/>
</head>
<body>
<div>
    <h1 class="article-name">${article.name}</h1>
    <div id="content"></div>
</div>
<script src="/marked.min.js"></script>
<script>
    $(document).ready(function(){
        $.ajax({
            url:"/article/content/${article.uuid}",
            success: function (result) {
                if(result.success){
                    document.getElementById('content').innerHTML =
                            marked(result.content.content);
                }
            }
        });
    });
</script>
</body>
</html>