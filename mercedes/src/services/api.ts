import axios from 'axios';
import { BASE_URL } from '../utils/constants';

export const fetchCarsData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}cars`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const fetchCarData = async (id: any) => {
    try {
        const response = await axios.get(`${BASE_URL}cars/${id}`);
        return response.data;
    } catch (error) {
        throw error;
    }
};
