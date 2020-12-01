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
    this.setState({ longURL: event.target.value });
  }

  handleSubmit(event) {
    console.log("A name was submitted: " + this.state.longURL);
    fetch("http://localhost:8080/short", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ longURL: this.state.longURL }),
    })
      .then((response) => {
        console.log(
          "response content type",
          response.headers.get("content-type")
        );
        console.debug(response);
        return response.json();
      })
      .then((response) => {
        this.setState({
          shortURL: response.shortURL,
        });
        console.log(response);
      });
    event.preventDefault();
  }

  render() {
    const styles = {
      container: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
      },

      form: {
        container: {
          display: "flex",
          flexDirection: "column",
          gap: "10px",
          maxWidth: "400px",
          width: "100%",
          margin: "10px",
        },
        input: {
          padding: "10px",
          border: "solid 1px #aaa",
          borderRadius: "30px",
          color: "#111",
          backgroundColor: "#ebebef",
        },
        submit: {
          backgroundColor: "#0cc367",
          borderRadius: "30px",
          textAlign: "center",
          fontSize: "20px",
          fontWeight: "bold",
          color: "white",
          border: "none",
          padding: "10px",
        },
      },
      result: {
        container: {
          display: 'flex',
          justifyContent: 'space-between',
          marginTop: '50px',
          backgroundColor: '#7bedb3',
          border: 'solid 1px #0ac365',
          padding: '5px',
          borderRadius: '10px',
          width: '100%',
          maxWidth: '350px'
        },

        url: {
          textDecoration: 'none',
          color: '#003d63',
          fontSize: '20px',
          padding: '5px 20px'
        },
        
        copy: {
          padding: '5px 20px',
          fontSize: '20px',
          fontWeight: 'bold',
          color: 'white',
          backgroundColor: 'transparent',
          border: 'solid 1px #057a3f',
          borderRadius: '10px',
          textTransform: 'uppercase'
        }

      }
    };

    return (
      <div style={styles.container}>
        <h1>Spring boot Shortner</h1>
        <form style={styles.form.container} onSubmit={this.handleSubmit}>
          <input
            style={styles.form.input}
            name="long-url"
            type="url"
            value={this.state.value}
            onChange={this.handleChange}
            placeholder="enter valid url"
            required
          />
          <button style={styles.form.submit} name="submit" type="submit">
            Short
          </button>
        </form>

        <div style={styles.result.container}>
          <a href="#" style={styles.result.url}>short URL</a>
          <button style={styles.result.copy}>Copy</button>
        </div>
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("react"));
