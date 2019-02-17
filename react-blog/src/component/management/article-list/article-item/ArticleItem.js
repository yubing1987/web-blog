import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import "./ArticleItem.css";
import {Button} from "antd";
class ArticleItem extends Component{
    state = {
        goEdit: false
    };

    render() {
        if(this.state.goEdit){
            return <Redirect to={"/management/edit?id=" + this.props.article.id}/>;
        }
        else {
            return <div className={"article-panel"}>
                <div className={"article-content"}>
                    <a href={"/#/article/" + this.props.article.id} target={"_blank"}>{this.props.article.title}</a>
                    <div className={"article-abstract"}>{this.props.article.abstractContent}</div>
                </div>
                <div className={"article-button"}>
                    <div style={{"marginBottom": "4px"}}>
                        <Button onClick={() => {this.setState({goEdit: true})}} style={{"width": "66px"}} size={"small"} icon={"edit"}>编辑</Button>
                    </div>
                    <div>
                        <Button style={{"width": "66px"}} size={"small"} type="danger" icon={"delete"}>删除</Button>
                    </div>
                </div>
            </div>
        }
    }
}

export default ArticleItem;