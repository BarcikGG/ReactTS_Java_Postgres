import { useNavigate } from "react-router-dom";

function NotFoundPage() {
  const navigate = useNavigate();
  const handleButtonClick = () => {
    navigate('/Catalog');
  };
  
  return (
    <div className=' flex flex-col mt-[4rem]'>
      <h1 className='text-[4rem] weight-900 text-center text-[#192457]'>Благодарим за заказ</h1>
      <p className='text-[2rem] text-center text-[#192457]'>Менеджер свяжется с вами в ближайшее время</p>
      <div className='flex justify-center'>
        <button className='w-[20%] h-[3rem] 
                rounded-[.2rem] border-[.1rem] 
                border-[black] mt-[2rem] hover:bg-[#192457] hover:text-white' onClick={handleButtonClick}>
            Каталог
        </button>
      </div>
    </div>
  );
}

export default NotFoundPage;