import React from 'react';
import Carousel from 'react-bootstrap/Carousel';
import Receta from './Receta';

const Recetas = ( {recetas} ) => {
  return(
    <div className = "ListRecetas">
      <Carousel fade>
      {recetas.map((receta, i) => {
        return(
          <Carousel.Item key = {i}>
            <Receta receta={receta}/>
          </Carousel.Item>
        )}
      )}
      </Carousel>
    </div>
  );
}

export default Recetas;
