var React = require('react');
var SimpleModal = require('./simpleModal');
var $ = require('jquery');

var Body = React.createClass({
  render: function() {

    var iframeStyle = {
      width: '100%',
      height: $(window).height() - 150
    }

    return (
      <div className='modal-body'>
        <iframe id='pdfViewer' src='/plugin/pdfjs-build/generic/web/viewer.html?file=/sample/page/pdf' style={iframeStyle} allowFullScreen='' webkitAllowFullScreen=''></iframe>
      </div>
    );
  }
});

var Footer = React.createClass({
  render: function() {
    return (
      <div className='modal-footer'>
        <button type='button' className='btn btn-default' data-dismiss='modal'>Close</button>
      </div>
    )
  }
});


var Modal = React.createClass({
  getDefaultProps() {
    return {
      id: 'modal-id',
      header: {
        title: 'Modal Title'
      }
    }
  },
  render: function() {

    var modalDialogStyle = {
      width: '100%',
      height: '100%',
      margin: 0,
      padding: 0
    };

    var modalContentStyle = {
      height: 'auto',
      minHeight: '100%',
      borderRadius: 0
    };

    return (
      <div id={this.props.id} className='modal fade' tabIndex='-1' role='dialog'>
        <div className='modal-dialog' style={modalDialogStyle}>
          <div className='modal-content' style={modalContentStyle}>
            <SimpleModal.Header data={this.props.header}/>
            <Body />
            <Footer />
          </div>
        </div>

      </div>
    );
  }
});

module.exports = {
  Modal: Modal
}
