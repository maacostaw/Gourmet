import React, { Fragment } from 'react';
import Recetarios from './Recetarios';
import AppNavBar from '../NavBar/AppNavBar';
import {useAxios}  from '../hooks/useAxios';

const HomeRecetarios = () => {
  const urlRecetarios = '/recetarios';

  const recetarios = useAxios(urlRecetarios);

  return (
    <Fragment>
      <AppNavBar titulo = 'Lista de Recetarios'/>
      <Recetarios recetarios = {recetarios} />
    </Fragment>
  );
}

export default HomeRecetarios;
