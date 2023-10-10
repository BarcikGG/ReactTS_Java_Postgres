import { useEffect, useState } from 'react';
import ICar from '../models/CarInterface';
import CarCard from './CarCard';
import { fetchCarsData } from '../services/api';

export default function Category({ number, text }: any) {
  const [data, setData] = useState<ICar[]>([]);

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

  return (
    <>
      <h1 className='text-[#192457] text-[3rem] font-[600] mt-[1rem]'>{text}</h1>
      <div className='flex flex-wrap'>
        {data
          .filter((carItem: any) => carItem.categoryID === number)
          .map((filteredItem: any) => (
            <CarCard key={filteredItem.id} item={filteredItem} />
          ))}
      </div>
    </>
  );
}
