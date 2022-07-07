import React from 'react';
import {Link} from 'react-router-dom';

const Recetario = ( {recetario} ) => {
  return(
    <div className = "card">
      <div
        className = "image"
        style = {{ 'background': `url('${recetario.imagen}')`,
                   'backgroundPosition': 'center',
                   'backgroundSize': 'cover',
                   'backgroundRepeat': 'noRepeat'}}
      >
        <div className = "text">{recetario.nombre}</div>
      </div>
      <div className = "text">
        <Link to = {`/recetarios/${recetario.id}`}>Ver detalle</Link>
      </div>
    </div>
  )
}

export default Recetario;
