var React = require('react');

var Header = React.createClass({
  render: function() {
    return (
      <div className='modal-header'>
        <button type='button' className='close' data-dismiss='modal' aria-label='close'>
          <span aria-hidden='true'>&times;</span>
        </button>
        <h4 className='modal-title'>{this.props.data.title}</h4>
      </div>
    );
  }
});

var Body = React.createClass({
  render: function() {

    var iframeStyle = {
      width: '100%',
      height: '700px'
    }

    return (
      <div className='modal-body'>
        <p>modal-body</p>
      </div>
    );
  }
});

var Footer = React.createClass({
  render: function() {
    return (
      <div className='modal-footer'>
        <button type='button' className='btn btn-default' data-dismiss='modal'>Close</button>
        <button type='button' className='btn btn-primary'>Save</button>
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
    return (
      <div id={this.props.id} className='modal fade' tabIndex='-1' role='dialog'>
        <div className='modal-dialog'>
          <div className='modal-content'>
            <Header data={this.props.header} />
            <Body />
            <Footer />
          </div>
        </div>

      </div>
    );
  }
});

module.exports = {
  Header: Header,
  Body: Body,
  Footer: Footer,
  Modal: Modal
}
