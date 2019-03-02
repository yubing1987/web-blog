import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Management from "./component/management/Management";

import Home from "./component/home/Home";
import Articles from "./component/mobile/articles/Articles";
import Article from "./component/mobile/article/Article";


import * as serviceWorker from './serviceWorker';
import {HashRouter, Route, Switch} from 'react-router-dom'

const SliderComponent = () => (
    <Switch>
        <Route exact path='/' component={Home} />
        <Route exact path='/mobile/' component={Articles} />
        <Route exact path='/mobile/list/' component={Articles} />
        <Route exact path='/mobile/list/:id' component={Articles} />
        <Route exact path='/mobile/article/:id' component={Article} />
        <Route path="/management/" component={Management}/>
    </Switch>
)

ReactDOM.render((
    <HashRouter >
        <SliderComponent />
    </HashRouter>

), document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
