var $ = require('jquery');
var config = require('../config');
var ui = require('../util/ui');
var React = require('react');
var ReactDom = require('react-dom');
var dashboard = require('../component/dashboard');

var local = {
  domId: 'dashboard',
  $dom: undefined
}

module.exports = {
  init: init
}

function init() {
  return new Promise(function(resolve, reject) {
    Promise.resolve()
      .then(open)
      .then(eventBind)
      .then(resolve)
    ;
  });
}

function eventBind() {
  return new Promise(function(resolve, reject) {
    local.$dom.click(function() {
      alert('clicked!');
    })
    resolve();
  });
}

function eventUnBind() {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

function open() {
  return new Promise(function(resolve, reject) {
    ui.open({
      reactElem: React.createElement(dashboard.DashBoardBox, {
        id: local.domId
      }),
      icon: 'fa fa-dashboard',
      title: 'dashboard',
      subTitle: 'Top page',
      breadcrumb: []
    })
      .then(function() {
        local.$dom = $('#' + local.domId);
      })
      .then(resolve)
    ;
  });
}
