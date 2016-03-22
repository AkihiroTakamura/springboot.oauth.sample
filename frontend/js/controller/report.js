var $ = require('jquery');
var config = require('../config');
var ui = require('../util/ui');
var React = require('react');
var ReactDom = require('react-dom');
var report = require('../component/report');
var reportModal = require('../component/pdfPreviewModal');

var local = {
  domId: 'report',
  $dom: undefined
}

var default_param = {
  id: 'report id',
  href: '/',
  title: 'report title',
  subTitle: 'report subtitle',
  reportList: []
}

var default_param_modal = {
  render_zone: '.modal-zone',
  id: 'modal-id'
}

module.exports = {
  init: init
}

function init(param) {
  return new Promise(function(resolve, reject) {
    Promise.resolve(param)
      .then(setParam)
      .then(open)
      .then(getReportList)
      .then(showReportList)
      .then(eventBind)
      .then(resolve)
    ;
  });
}

function setParam(param) {
  return new Promise(function(resolve, reject) {
    resolve($.extend(true, {}, default_param, param));
  });
}

function eventBind(param) {
  return new Promise(function(resolve, reject) {

    local.$dom.on('click', '.btn-pdf', function(e) {
      var options = {
        render_zone: '.modal-zone',
        id: 'report-modal'
      };

      Promise.resolve(options)
        .then(setModalParam)
        .then(renderModal)
        .then(showModal)
      ;
    });

    resolve(param);
  });
}

function eventUnBind(param) {
  return new Promise(function(resolve, reject) {
    resolve(param);
  });
}

function open(param) {
  return new Promise(function(resolve, reject) {

    ui.open({
      reactElem: React.createElement(report.ReportBox, {
        id: local.domId
      }),
      icon: 'fa fa-paperclip',
      title: param.title,
      subTitle: param.subTitle,
      breadcrumb: [param]
    })
      .then(function() {
        local.$dom = $('#' + local.domId);
        resolve(param);
      })
    ;
  });
}

function getReportList(param) {
  return new Promise(function(resolve, reject) {

    //TODO: ここでajax発行してレポートデータを取得する予定
    param.reportList.push(['1', 'サンプル１', '2016-01-01 00:00:00', '']);
    param.reportList.push(['2', 'サンプル２', '2016-01-02 00:00:00', '']);

    resolve(param);

  });
}

function showReportList(param) {
  return new Promise(function(resolve, reject) {

    local.$dom.find('#reportList').DataTable({
      data: param.reportList,
      columns: [
        {title: 'id'},
        {title: 'name'},
        {title: 'createdAt'},
        {title: 'controles'}
      ]
    });

    local.$dom.find('#reportList').find('td:last-child')
      .append(
        $('<span></span>')
          .addClass('btn btn-primary btn-pdf')
          .html('PDF')
        ,
        $('<span></span>')
          .addClass('btn btn-primary btn-csv')
          .html('CSV')
      )
    ;

    resolve(param);
  });
}

function setModalParam(param) {
  return new Promise(function(resolve, reject) {
    resolve($.extend(true, {}, default_param_modal, param));
  });
}

function renderModal(param) {
  return new Promise(function(resolve, reject) {
    ReactDom.render(
      React.createElement(reportModal.Modal, param),
      $(param.render_zone).get(0)
    );

    resolve(param);
  });
}

function showModal(param) {
  return new Promise(function(resolve, reject) {
    $(param.render_zone).find('#' + param.id).modal('show');
    resolve(param);
  });
}


