import CarCard from './CarCard';

export default function Category({ data, number, text }: any) {
  return (
    <>
      <h1 className='text-[#192457] text-[3rem] font-[600]'>{text}</h1>
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
