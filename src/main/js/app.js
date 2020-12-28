const React = require("react");
const ReactDOM = require("react-dom");

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { longURL: "", shortURL: "" };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {}

  handleChange(event) {
    this.setState({ longURL: event.target.value});
  }

  handleSubmit(event) {
    console.log("A name was submitted: " + this.state.longURL);
    fetch("http://localhost:8080/short", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json",
      },
      body: JSON.stringify({ "longURL": this.state.longURL}),
    })
      .then((response) => {
          console.log("response content type", response.headers.get('content-type'));
          console.debug(response);
          return response.json();})
      .then((response) => {
        this.setState({
          shortURL: response.shortURL,
        });
        console.log(response);
      });
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
