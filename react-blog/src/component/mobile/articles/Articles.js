import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi";
import ArticleItem from "./article-item/ArticleItem";

import "./Articles.css"

class Articles extends Component{

    constructor(props){
        super(props);
        this.state = {
            articles: {}
        }
        this.loadArticle(1);
    }

    loadArticle(page){
        if(this.props.match.params.id){
        }
        else{
            ArticleApi.queryPublishedArticleList(page, 10, "")
                .then((data) => {
                    this.setState({articles: data});
                })
        }
    }

    render(){
        let articleList = null;
        if(this.state.articles.items){
            articleList = this.state.articles.items.map((item) => {
                return <ArticleItem key = {item.id} article={item}/>
            });
        }
        return <div style={{"background": "#eee",  "padding":"30px"}}>
            {articleList}
        </div>
    }
}

export default Articles;