const React = require("react");
const ReactDOM = require("react-dom");

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {longURL: ''};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {}

  handleChange(event) {
    this.setState({ longURL: event.target.value });
  }

  handleSubmit(event) {
    alert("A name was submitted: " + this.state.longURL);
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <input
            name="long-url"
            type="url"
            value={this.state.value}
            onChange={this.handleChange}
            placeholder="enter valid url"
          />
          <input name="submit" type="submit" />
        </form>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
