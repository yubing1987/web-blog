import React, { Component } from 'react';
import ArticleApi from "../../../server/ArticleApi"
import {Alert, Input, Upload, Icon, Modal, message, Button} from "antd";
import "./ArticleEditor.css"

const { TextArea } = Input;

class ArticleEditor  extends Component{
    constructor(props){
        super(props);
        this.state = {
            previewVisible: false,
            previewImage: '',
            hasError: false,
            id: ArticleEditor.getArticleId(this.props.location.search.trim()),
            article: {},
            change: false,
            fileList : []
        };
        if(this.state.id){
            ArticleApi.getArticleById(this.state.id)
                .then((data) => {
                    let pic = [];
                    if(data.picture && data.picture.length > 0){
                        pic.push({
                            uid: '-1',
                            name: '标题图片',
                            status: 'done',
                            url: "/api/image/" + data.picture
                        });
                    }
                    this.setState({article: data, fileList : pic});
                })
                .catch(() => {
                    this.setState({hasError: true});
                });
        }
    }

    static getArticleId(search){
        if(!search || search.length < 1){
            return undefined;
        }
        let s = search.substring(1);
        let param = s.split("&");
        for(let i = 0; i < param.length; i++){
            let k = param[i].split("=");
            if(k.length === 2 && k[0] === "id"){
                return k[1];
            }
        }
        return undefined;
    }

    handleCancel = () => this.setState({ previewVisible: false });

    handlePreview = (file) => {
        this.setState({
            previewImage: file.url || file.thumbUrl,
            previewVisible: true,
        });
    };

    handleChange = ({ fileList }) => {
        if(fileList.length > 1){
            return;
        }
        this.setState({ fileList });
    };

    beforeUpload = (file) => {
        if(this.state.fileList.length >= 1){
            message.error('目前只支持一张文章封面图片!');
            return false;
        }
        const fileOk = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!fileOk) {
            message.error('必须是JPG或者PNG图片!');
            return false;
        }
        const sizeOk = file.size / 1024 / 1024 < 5;
        if (!sizeOk) {
            message.error('图片大小必须小于5MB!');
            return false;
        }
    };

    render(){
        if(this.state.hasError){
            return <Alert message="该文章不存在或者已经被删除了！" type="error" />
        }
        else{
            const uploadButton = (
                <div>
                    <Icon type="plus" />
                    <div className="ant-upload-text">Upload</div>
                </div>
            );
            const { previewVisible, previewImage, fileList } = this.state;
            return <div>
                <div className = {"view-item"}>
                    <span >标题：</span>
                </div>
                <div className = {"view-item"}>
                    <Input placeholder="请输入标题" value={this.state.article.title} />
                </div>
                <div className = {"view-item"}>
                    <span>正文：</span>
                </div>
                <div className = {"view-item"}>
                    <TextArea rows={20}  autosize={false} value={this.state.article.content}/>
                </div>
                <div className = {"view-item"}>
                    <span>摘要：</span>
                </div>
                <div className = {"view-item"}>
                    <TextArea rows={4}  autosize={false} value={this.state.article.abstractContent}/>
                </div>
                <div className = {"view-item"}>
                    <span>封面图片：</span>
                </div>
                <div className = {"view-item picture-view"}>
                    <Upload
                        name={'picture'}
                        action={'/api/image/upload'}
                        fileList = {fileList}
                        onChange={this.handleChange}
                        onPreview={this.handlePreview}
                        listType = {"picture-card"}
                        beforeUpload={this.beforeUpload}
                    >
                        {uploadButton}
                    </Upload>
                    <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
                        <img alt="example" style={{ width: '100%' }} src={previewImage} />
                    </Modal>
                </div>
                <div className = {"view-item"} style={{"textAlign":"center"}}>
                    <Button type={"primary"} style={{"marginRight": "20px"}}>保存</Button>
                    <Button>发布</Button>
                </div>
            </div>
        }
    }
}

export default ArticleEditor;