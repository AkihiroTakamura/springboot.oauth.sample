var React = require('react');

var ContentTitle = React.createClass({
  render: function() {
    return (
      <h1 className='content-header-title'>
        <i className={this.props.icon}></i>
        {this.props.title}
        <small>{this.props.subTitle}</small>
      </h1>
    );
  }
});

var BreadCrumb = React.createClass({
  render: function() {
    return (
      <li>
        <a href={this.props.href}>
          {this.props.title}
        </a>
      </li>
    );
  }
});

var BreadCrumbList = React.createClass({
  render: function() {
    var breadcrumbList = this.props.breadcrumb.map(function(breadcrumb) {
      return (
        <BreadCrumb href={breadcrumb.href} title={breadcrumb.title} key={breadcrumb.id} />
      );
    });

    return (
      <ol className='breadcrumb'>
      <BreadCrumb href='/' title='top' key='0' />
        {breadcrumbList}
      </ol>
    );
  }
});

var ContentHeader = React.createClass({
  render: function() {
    return (
      <section className="content-header">
        <ContentTitle
          icon={this.props.icon}
          title={this.props.title}
          subTitle={this.props.subTitle}
        />
        <BreadCrumbList breadcrumb={this.props.breadcrumb} />
      </section>
    );
  }
});

module.exports = {
  ContentHeader: ContentHeader
}
