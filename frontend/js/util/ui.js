$ = jQuery = require('jquery');
var config = require('../config');
var React = require('react');
var ReactDom = require('react-dom');

var contentHeader = require('../component/content-header');
var $contentZone = $(config.app.renderFields.content);

var default_param = {
  reactElem: undefined,   // contentとなるReactコンポーネント
  icon: 'fa fa-paperclip',  // content-headerに表示するアイコン
  title: 'Title Not Set', // content-headerに表示する機能名
  subTitle: 'Sub Title Not Set', // content-headerに表示する機能名の説明
  breadcrumb: []  // breadcrumbに表示する内容、配列格納順に表示 {id: xx, href: xx, name: xx}
}

module.exports = {
  open: open
}

function open(param) {
  return new Promise(function(resolve, reject) {
    Promise.resolve(param)
      .then(setParam)
      .then(initContentZone)
      .then(renderContentHeader)
      .then(renderContent)
      .then(resolve)
      .catch(reject)
    ;
  });
}

function setParam(param) {
  return new Promise(function(resolve, reject) {
    resolve($.extend(true, {}, default_param, param));
  });
}

function initContentZone(param) {
  return new Promise(function(resolve, reject) {
    $contentZone.empty();
    resolve(param);
  });
}

function renderContentHeader(param) {
  return new Promise(function(resolve, reject) {
    ReactDom.render(
      React.createElement(contentHeader.ContentHeader, param),
      $contentZone.get(0)
    );

    resolve(param);
  })
}

function renderContent(param) {
  return new Promise(function(resolve, reject) {

    if (!param.reactElem) return reject('invalid parameter. require reactElem');

    var $content = $contentZone
      .append(
        $('<section></section>').addClass('content')
      )
      .find('.content')
    ;

    ReactDom.render(
      param.reactElem,
      $content.get(0)
    );

    resolve(param);
  });
}
