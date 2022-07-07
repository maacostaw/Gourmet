import React from 'react';
import Recetario from './Recetario';

const Recetarios = ( {recetarios} ) => {
  return(
    <div className='ListRecetarios'>
    {recetarios.map( (recetario, i) => {
      return(
        <Recetario recetario = {recetario} key = {i}/>
      )}
    )}
    </div>
  );
}

export default Recetarios;
