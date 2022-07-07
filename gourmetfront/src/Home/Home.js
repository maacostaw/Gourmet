import React from 'react';
import {Link} from 'react-router-dom';

const Home = () => {
  return (
    <div className="Home">
      <h1>Gourmet</h1>
      <Link to = '/recetarios'> Ver Recetarios</Link>
    </div>
  );
};

export default Home;
