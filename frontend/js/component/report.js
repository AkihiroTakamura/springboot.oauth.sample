var React = require('react');

var ReportBox = React.createClass({
  render: function() {
    return (
      <div id={this.props.id} className="box-report">
        <table id="reportList" className='display' width='100%' />
      </div>
    );
  }
});

module.exports = {
  ReportBox: ReportBox
}
