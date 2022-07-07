import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Home from './Home/Home';
import HomeRecetarios from './Recetarios/HomeRecetarios';
import HomeRecetas from './Recetas/HomeRecetas';

const App = () => {

  return (
    <Routes>
      <Route path ='/' element = {<Home/>} />
      <Route path = '/recetarios' element = {<HomeRecetarios/>} />
      <Route path = '/recetarios/:recetarioId' element = {<HomeRecetas/>} />
    </Routes>
  );
}

export default App;
