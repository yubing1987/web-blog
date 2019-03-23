import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi";
import {
    Alert, Icon
} from 'antd';
import { Redirect} from 'react-router-dom'

import "./Article.css"
import Markdown from "../../markdown/Markdown"

class Article extends Component{
    constructor(props){
        super(props);
        this.state = {
            error: !this.props.match.params.id,
            goHome: false,
            loaded: false,
            showFollow: false
        };
        document.title = "Java技术分享";
        if(!this.state.error){
            this.loadArticle();
        }
    }

    loadArticle(){
        ArticleApi.getArticleById(this.props.match.params.id)
            .then((article) => {
                document.title = article.title;
                this.setState({article: article, loaded: true});
            })
            .catch((c) => {
                document.title = "错误";
                this.setState({error: true});
            });
    }

    render(){
        let content = null;
        if(this.state.error){
            content =
                <div>
                    <Alert
                        message="错误信息"
                        description="文章没有找到或者获取文章信息出错！"
                        type="error"
                        showIcon
                    />
                </div>
        }
        else if(this.state.goHome){
            content = <Redirect to={"/mobile/"}/>;
        }
        else if(this.state.loaded){
            content = <div>
                <div className={"article-content-title"}>{this.state.article.title}</div>
                <div style={{"margin" :"10px 0"}}>
                    <Markdown type={"mobile"} content = {this.state.article.content}/>
                </div>

            </div>
        }

        return <div style={{"minHeight":"100%"}}>
            <div className={"article-panel-view"}>
                {content}
            </div>
            {
                this.state.showFollow?<div className={"follow-view"}>
                        <img alt={""} width={120} src={"http://java-code.net/img/toutiao.jpeg"} />
                        <img alt={""} width={120} src={"http://java-code.net/img/weixin.jpg"} />
                    </div>
                    :null
            }
            <div className={"back-btn"} onClick={() => {this.setState({goHome: true, error: false})}}>
                <Icon type="rollback"/>
            </div>
            <div className={"follow-btn"} onClick={() => {this.setState({showFollow: !this.state.showFollow})}}>
                {
                    this.state.showFollow?<Icon type="minus-square" />:<Icon type="plus-square" />
                }
            </div>
        </div>
    }
}

export default Article;
