import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi";
import {
    Alert, Button
} from 'antd';
import { Redirect} from 'react-router-dom'

import "./Article.css"

const ReactMarkdown = require('react-markdown')

class Article extends Component{
    constructor(props){
        super(props);
        this.state = {
            error: !this.props.match.params.id,
            goHome: false,
            loaded: false
        };
        if(!this.state.error){
            this.loadArticle();
        }
    }

    loadArticle(){
        ArticleApi.getArticleById(this.props.match.params.id)
            .then((article) => {
                this.setState({article: article, loaded: true});
            })
            .catch((c) => {
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
                    <div style={{"textAlign": "center", "marginTop":"20px"}}>
                        <Button type="primary" onClick={() => {this.setState({goHome: true, error: false})}}>回主页</Button>
                    </div>
                </div>
        }
        else if(this.state.goHome){
            content = <Redirect to={"/mobile/"}/>;
        }
        else if(this.state.loaded){
            content = <div>
                <div className={"article-content-title"}>{this.state.article.title}</div>
                <div style={{"margin" :"10px 0"}}>
                    <ReactMarkdown source = {this.state.article.content}/>
                </div>
            </div>
        }

        return <div className={"article-panel-view"}>
            {content}
        </div>
    }
}

export default Article;
