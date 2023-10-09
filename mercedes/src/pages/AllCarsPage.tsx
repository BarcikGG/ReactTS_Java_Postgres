import { useEffect, useState } from 'react';
import CarCard from '../components/CarCard';
import ICar from '../models/CarInterface';
import { fetchCarsData } from '../services/api';

export default function AllCars() {

  const [data, setData] = useState<ICar[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const carData = await fetchCarsData();
        setData(carData);
      } catch (error) {
        console.error('Ошибка при получении данных:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className='px-[15rem] '>
      <h1 className='text-[#192457] text-[3rem] font-[600]'>C-class</h1>
        <div className='flex flex-wrap'>
          {data.filter((item) => item.categoryID === 2)
          .map((item) => (
            <CarCard key={item.id} item={item} />
          ))}
        </div>
      <h1 className='text-[#192457] text-[3rem] font-[600] mt-[2rem]'>E-class</h1>
        <div className='flex flex-wrap'>
          {data.map((item) => (
            item.categoryID === 3 && (
              <CarCard item={item}/>
            )
          ))}
        </div>
      <h1 className='text-[#192457] text-[3rem] font-[600] mt-[2rem]'>S-class</h1>
        <div className='flex flex-wrap'>
          {data.map((item) => (
            item.categoryID === 4 && (
              <CarCard item={item}/>
            )
          ))}
        </div>

        <h1 className='text-[#192457] text-[3rem] font-[600] mt-[2rem]'>A-class</h1>
        <div className='flex flex-wrap'>
          {data.map((item) => (
            item.categoryID === 1 && (
              <CarCard item={item}/>
            )
          ))}
        </div>
    </div>
  )
}