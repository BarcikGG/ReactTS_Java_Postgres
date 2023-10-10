import axios from 'axios';
import { BASE_URL } from '../utils/constants';

export const fetchCategoriesData = async () => {
  try {
    const response = await axios.get(`${BASE_URL}categories`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

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

export const sendMailApi = async(formData: any) => {
  const url = `${BASE_URL}/sendEmail?fullName=${formData.fullName}
  &email=${formData.email}
  &phoneNumber=${formData.phoneNumber}
  &carName=${formData.carName}`;
  try {
    const response = await axios.get(url);
    console.log(response.data);
  } catch (error) {
      console.error(error);
  }
};
