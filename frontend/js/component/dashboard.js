var React = require('react');

var DashBoardBox = React.createClass({
  render: function() {
    return (
      <div id={this.props.id} className="dashBoardBox">
        Hello, world! I am a DashBoard Box.
      </div>
    );
  }
});

module.exports = {
  DashBoardBox: DashBoardBox
}
