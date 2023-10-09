import { useEffect, useState } from 'react';
import CarCard from '../components/CarCard';
import ICar from '../models/CarInterface';
import { fetchCarsData, fetchCategoriesData } from '../services/api';
import Category from '../components/Category';
import ICategory from '../models/CategoryInterface';

export default function AllCars() {

  const [data, setData] = useState<ICar[]>([]);
  const [dataCategories, setDataCategories] = useState<ICategory[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const carData = await fetchCarsData();
        setData(carData);
      } catch (error) {
        console.error('Ошибка при получении данных машин:', error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const categoriesData = await fetchCategoriesData();
        setDataCategories(categoriesData);
      } catch (error) {
        console.error('Ошибка при получении данных категорий:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className='px-[15rem] '>
      {dataCategories.map((item) => (
          <Category data={data} number={item.id} text={item.name} />
        ))}
    </div>
  )
}