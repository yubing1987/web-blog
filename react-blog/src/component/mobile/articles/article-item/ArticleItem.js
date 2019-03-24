import React, { Component } from 'react';
import moment from "moment";
import { Redirect} from 'react-router-dom'

import "./ArticleItem.css"

class ArticleItem extends Component{

    render(){
        return <div className={"article-item"} onClick={() => {this.goDetail()}}>
            <img alt={"Article"} src={"/api/image/" + this.props.article.picture}/>
            <div className={"article-title"}>
                {this.props.article.title}
            </div>
            <div className={"article-view-abstract"} style={{"WebkitBoxOrient": "vertical"}}>
                {this.props.article.abstractContent}
            </div>
            <div className={"article-view-date"}>
                发表时间：{moment(this.props.article.modifyDate).format("YYYY-MM-DD HH:mm:ss")}
            </div>
        </div>
    }

    goDetail(){
        window.location.href="/mobile/article/" + this.props.article.id;
    }
}

export default ArticleItem;
