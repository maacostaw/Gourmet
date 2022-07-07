import React, {Fragment} from 'react';

const Receta = ( {receta} ) => {
  function toString (ingrediente) {
    let toString = ingrediente.alimento.nombre;
    const hayUnidadDeMedida = ingrediente.unidadDeMedida !== undefined;
    const hayMedida = ingrediente.medida !== undefined;
    if(hayUnidadDeMedida && hayMedida) {
      toString += " " + ingrediente.medida + " " + ingrediente.unidadDeMedida;
    }
    return toString;
  }

  return (
    <Fragment>
      <div className = "left">
        <div className = "inside">
          <div className = 'title'>
            {receta.nombre}
          </div>
          <ul>
          {receta.ingredientes.map((ingrediente, i) => {
            return(
              <li key = {i}>{toString(ingrediente)}</li>
            )}
          )}
          </ul>
        </div>
      </div>
      <div className = "right">
        <div
          className = "inside"
          style = {{'background': `url('${receta.imagen}')`,
                    'backgroundPosition': 'center',
                    'backgroundSize': 'cover',
                    'backgroundRepeat': 'noRepeat'}}
        >
        </div>
      </div>
    </Fragment>
  );
}

export default Receta;
