var $ = require('jquery');
var config = require('../config');

module.exports = {
  init: init
}

function init() {
  return new Promise(function(resolve, reject) {
    Promise.resolve()
      .then(eventBind)
      .then(resolve)
    ;
  });
}

function eventBind() {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

function eventUnBind() {
  return new Promise(function(resolve, reject) {
    resolve();
  });
}

