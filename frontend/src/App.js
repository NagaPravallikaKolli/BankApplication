import './App.css';
import Appbar from "./components/Appbar"
import Navbar from "./components/Navbar"
import Home from "./components/Home"

function App() {
  return (
    <div className="App">
      <Appbar/>
      <Home/>
      <Navbar/>
    </div>
  );
}

export default App;
