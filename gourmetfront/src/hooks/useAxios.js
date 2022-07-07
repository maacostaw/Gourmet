import axios from 'axios';
import { useState, useEffect } from "react";
import {serverURL} from "../AppConfig.js";

const useAxios = (url) => {
  const[data, setData] = useState([]);

  useEffect(() => {
    axios.get(serverURL+url)
    .then((result) => {
      setData(result.data);
    })
    .catch(error => {
      console.log(error)
    });
  },[url]);

  return data;
}

export {useAxios};
