import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi";
import ArticleItem from "./article-item/ArticleItem";

import "./Articles.css"

class Articles extends Component{

    constructor(props){
        super(props);
        this.state = {
            articles: []
        };
        document.title = "文章列表";
        this.loadArticle(1);
    }

    loadArticle(page){
        if(this.props.match.params.id){
            ArticleApi.getRelatedArticle(this.props.match.params.id)
                .then((data) => {
                    this.setState({articles: data});
                });
        }
        else{
            ArticleApi.queryPublishedArticleList(page, 10, "")
                .then((data) => {
                    this.setState({articles: data.items});
                })
        }
    }

    render(){
        let articleList = null;
        if(this.state.articles){
            articleList = this.state.articles.map((item) => {
                return <ArticleItem key = {item.id} article={item}/>
            });
        }
        return <div style={{"background": "#eee",  "padding":"30px"}}>
            {articleList}
        </div>
    }
}

export default Articles;