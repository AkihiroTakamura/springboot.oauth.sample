var $ = require('jquery');
var config = require('../config');
var reportController = require('../controller/report');

var $dom = $('.main-sidebar.menu');

module.exports = {
  init: init,
  show: show,
  hide: hide,
  destroy: destroy
}

function init() {
  return new Promise(function(resolve, reject) {
    Promise.resolve()
      .then(eventBind)
      .then(show)
      .then(resolve);
  });
}

function show() {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

function hide() {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

function destroy() {
  return new Promise(function(resolve, reject) {
    Promise.resolve()
      .then(hide)
      .then(eventUnbind)
      .then(resolve);
  });
}

function eventBind() {
  return new Promise(function(resolve, reject) {
    $dom.on('click', '.menu-item', onItemClicked);
    resolve();
  });
}

function eventUnBind() {
  return new Promise(function(resolve, reject) {
    $dom.off('click', '.menu-item');
    resolve();
  });
}

function onItemClicked(e) {
  var $target = $(e.target).closest('.menu-item');

  switch ($target.attr('data-kind')) {
    case 'report':
      reportController.init({
        id: $target.attr('data-id'),
        href: $target.attr('data-href'),
        title: $target.attr('data-title'),
        subTitle: $target.attr('data-subTitle')
      });
      break;
    default:
      console.log('unknown data-kind:' + $target.attr('data-kind'));
      break;
  }


}



