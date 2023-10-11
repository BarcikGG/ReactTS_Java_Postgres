import { useNavigate } from "react-router-dom";


export default function HomePage() {
  const navigate = useNavigate();
  const handleButtonClick = () => {
    navigate(`/Configure/11`);
  };
  
  return (
    <div className="h-[90vh] bg-gtr flex justify-between bg-cover bg-no-repeat px-10">
      <h1 className="text-white text-[4rem] max-w-[50rem]"
      >Explore new Mercedes-Benz GT-R ROADSTER</h1>
      <h1 className="text-[2rem] text-gray-600 text-right w-[9rem]">850 hp 360 km/h AMG + </h1>
      <button className="bg-white w-[15rem] h-[4rem] 
      rounded-[5rem] text-[#192457] 
      absolute mt-[34rem] ml-[77rem]"
      onClick={handleButtonClick}>configure for you</button>
    </div>
  )
}
