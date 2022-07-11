import React from "react";
import './scss/app.scss'; // npm install sass
import ProductItem from "./components/ProductItem";
import {Route, Routes} from "react-router-dom";
import Basket from "./components/Basket";


function App() {

  return (
      <div className="container">
          <Routes>
              <Route path="/" element = {<ProductItem/>}/>
              <Route path="/basket" element = {<Basket/>}/>
          </Routes>
          <p className="placeholder-glow">
              <span className="placeholder col-12"/>
          </p>
      </div>
  );
}

export default App;
