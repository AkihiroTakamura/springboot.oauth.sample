// =======================
// get instance we need
// =======================
$ = jQuery = require('jquery');
window.Tether = require('tether');
var bootstrap = require('bootstrap');
var domready = require('domready');
var adminLte = require('admin-lte/dist/js/app.min.js');
var config = require('./config');
var error = require('./error');

// =======================
// jQuery plugins
// =======================
var noty = require('noty');
$.noty.defaults.layout = 'topRight';
$.noty.defaults.timeout = 3000;
var datatable = require('datatables')();  // datatablesを有効化するにはfunction実行が必要

// =======================
// css
// =======================
var cssify = require('cssify');
cssify.byUrl('//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css');
cssify.byUrl('//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css');
cssify.byUrl('//cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css')

var styleNode = require('../../src/main/resources/public/css/style.css');

// =======================
// Controllers
// =======================
var navbar = require('./controller/navbar');
var menu = require('./controller/menu');
var dashboard = require('./controller/dashboard');

// =======================
// jQuery setting
// =======================
$.ajaxSetup({
  error: function(xhr) {
    if (xhr.status == 401) {
      throw new error.UnAuthorizedException();
    }
  }
})

// =======================
// global error catch handler
// =======================
window.onerror = function(msg, file, line, column, err){
  if (!err || !err.name) return;

  switch(err.name) {
    case 'unauthorizederror':
      location.href = '/';
      break;
    default:
      break;
  }
}

// =======================
// entry point
// =======================
domready(function() {
  $("body").addClass('skin-blue-light sidebar-mini');

  $("body").fadeIn(config.fadeInterval, function() {
    Promise.resolve()
      .then(navbar.init)
      .then(menu.init)
      .then(open)
      .catch(error.show)
    ;
  });
})

function open() {
  return new Promise(function(resolve, reject) {
    var kind = $('body').find('#param-kind').val();
    var id     = $('body').find('#param-id').val();
    console.log('param kind=' + kind + ' id=' + id);

    if (!kind || !id) return top();

    var menuItem = $(".menu-item[data-kind="+ kind +"][data-id="+ id +"]");
    if (!menuItem || menuItem.length == 0) return top();

    $(menuItem).trigger('click');

  });
}

function top() {
  return new Promise(function(resolve, reject) {
    var top = require('./controller/dashboard');
    top.init();
    resolve();
  });
}
