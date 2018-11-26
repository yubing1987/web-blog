<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
    <meta charset="UTF-8">
    <title>${article.name}</title>
</head>
<body>
<div>
    <h1 class="article-name">${article.name}</h1>
    <div id="content"></div>
</div>
<script src="/marked.min.js"></script>
<script>
    document.getElementById('content').innerHTML =
            marked("${article.content}");
</script>
</body>
</html>