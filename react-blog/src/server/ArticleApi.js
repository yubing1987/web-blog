import Request from "../utils/Request"
let request = new Request();
class ArticleApi{
    static queryArticleList(page, size, key){
        return request.get("/api/article/list",{page: page, size: size, key: key});
    }

    static getArticleById(id){
        return request.get("/api/article/draft/" + id);
    }

    static articleEdited(article){
        return request.post("/api/article/edited", article);
    }

    static addArticle(article){
        return request.post("/api/article/add", article);
    }

    static articlePublished(article){
        return request.post("/api/article/" + article.id + "/published/true");
    }
}

export default ArticleApi;