const React = require('react');
const ReactDOM = require('react-dom'); 


class App extends React.Component { 

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() { 
	}

	render() {
		return (
			<div> hello world  again</div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)