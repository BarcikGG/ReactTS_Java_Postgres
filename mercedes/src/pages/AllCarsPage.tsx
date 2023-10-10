import { useEffect, useState } from 'react';
import { fetchCategoriesData } from '../services/api';
import Category from '../components/Category';
import ICategory from '../models/CategoryInterface';

export default function AllCars() {
  const [dataCategories, setDataCategories] = useState<ICategory[]>([]);

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

  dataCategories.sort((a, b) => a.id - b.id);

  return (
    <div className='px-[15rem] '>
      {dataCategories.map((item) => (
          <Category number={item.id} text={item.name} />
        ))}
    </div>
  )
}