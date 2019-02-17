import Request from "../utils/Request"
let request = new Request();
class ArticleApi{
    static queryArticleList(page, size, key){
        return request.get("/api/article/list",{page: page, size: size, key: key});
    }

    static getArticleById(id){
        return request.get("/api/article/" + id);
    }
}

export default ArticleApi;