import { useNavigate } from "react-router-dom";
import ICar from "../models/CarInterface";

interface CarCardProps {
    item: ICar;
  }

const CarCard: React.FC<CarCardProps> = ({ item }) => {
  const navigate = useNavigate();
  const handleButtonClick = () => {
    navigate(`/Configure/${item.id}`);
  };

  return (
    <div className='flex flex-col p-[5px] mr-[2rem] bg-white w-[20rem] h-[16rem] mt-[1rem] 
              rounded-[.3rem] border-[.1rem] border-[#192457] text-[#192457]'>
        <img src={item.img_url} alt="" 
        className='h-[10rem] w-[20rem]'/>
        <h3 className='text-center text-black mb-[.6rem] text-[1.3rem] font-semibold'>{item.name}</h3>
        <div className='flex justify-center'>
          <button  className='w-[90%] h-[2rem] 
            rounded-[.5rem] border-[.1rem] 
            border-[black] hover:bg-[#192457] hover:text-white'
            onClick={handleButtonClick}>
            configure
          </button>
        </div>
    </div>
  )
}
export default CarCard;