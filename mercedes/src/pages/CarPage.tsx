import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ICar from "../models/CarInterface";
import { fetchCarData } from "../services/api";
import DropdownSelect from "../components/DropDownSelect";
import ModalBuy from "../modal/ModalBuy";

export default function Car() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { id } = useParams();
  const [data, setData] = useState<ICar | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const carData = await fetchCarData(id);
        setData(carData);
      } catch (error) {
        console.error('Ошибка при получении данных:', error);
        navigate('/404');
      }
    };

    fetchData();
  }, [id, navigate]);

  const optionsHP = [
    { value: `${data?.hp}`, label: `${data?.hp} л.с.` },
    { value: `${data?.hp}`, label: `${data?.hp} л.с.` },
    { value: `${data?.hp}`, label: `${data?.hp} л.с.` },
  ];

  return (
    <div>
      {data ? (
        <div className="flex justify-between px-[15rem] pt-[2rem]">
          <div className="w-[70%]">
            <h1 className="font-600 text-[4rem]">{data.name}</h1>
            <img src={data.img_url} alt="img_pic" className="w-full 
            h-[25rem] rounded-[.5rem] border-[.1rem]
            border-[#192457] mt-[1rem]"/>
            <div className="flex justify-between">
              <h1 className="font-600 text-[2rem] mt-[1.6rem]">Price: ${data.price}</h1>
              <button  className='w-[20rem] h-[2rem] 
              rounded-[.5rem] border-[.1rem] mt-[2rem]
              border-[black] hover:bg-[#192457] hover:text-white'
              onClick={() => setIsModalOpen(true)}>
              buy
              </button>
            </div>
          </div>
          <div className="w-[25%] text-left mt-[6.5rem] text-[2rem]">
            <div className="flex items-center">
              <h3 className="mr-[.5rem]">Двигатель: </h3>
              <DropdownSelect options={optionsHP} />
            </div>
            <h3>Год: {data.year}</h3>
            <h3>Купе: {data.coupe ? "Да" : "Нет"}</h3>
            <h3>AMG: {data.amg ? "Да" : "Нет"}</h3>
            <br />
            <h3>Описание</h3>
            <p>{data.description}</p>
          </div>
          <ModalBuy isOpen={isModalOpen} CarName={data.name} onClose={() => setIsModalOpen(false)}/>
        </div>
      ) : (
        <div>
          <h1 className='text-[4rem] weight-900 text-center text-[#192457]'>Машина с указанным ID не найдена.</h1>
        </div>
      )}
    </div>
  );
}
