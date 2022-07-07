import React, { Fragment } from 'react';
import { useParams } from 'react-router-dom';
import Recetas from './Recetas';
import AppNavBar from '../NavBar/AppNavBar';
import {useAxios} from '../hooks/useAxios';

const HomeRecetas = () => {
  const { recetarioId } = useParams();

  const recetarioURL = `/recetarios/${recetarioId}`;
  const recetario = useAxios(recetarioURL);

  const recetasURL = `/recetarios/${recetarioId}/recetas`;
  const recetas = useAxios(recetasURL);

  return (
    <Fragment>
      <AppNavBar titulo = {recetario.nombre} />
      <Recetas recetas = {recetas} />
    </Fragment>
  );
}
export default HomeRecetas;
