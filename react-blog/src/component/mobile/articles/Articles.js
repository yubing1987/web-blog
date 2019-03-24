import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi";
import ArticleItem from "./article-item/ArticleItem";
import {Icon} from "antd";
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
        return <div style={{"background": "#eee",  "padding":"30px","minHeight": "100%"}}>
            {articleList}
            <div className={"follow-btn"} onClick={() => {this.setState({showFollow: !this.state.showFollow})}}>
                {
                    this.state.showFollow?<Icon type="minus-square" />:<Icon type="plus-square" />
                }关注
            </div>
            {
                this.state.showFollow?<div className={"follow-view"}>
                        <img alt={""} width={120} src={"http://java-code.net/img/toutiao.jpeg"} />
                        <img alt={""} width={120} src={"http://java-code.net/img/weixin.jpg"} />
                    </div>
                    :null
            }
        </div>
    }
}

export default Articles;