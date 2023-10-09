import { useState } from 'react';

function DropdownSelect({ options }: DropdownSelectProps) {
  const [selectedOption, setSelectedOption] = useState('');

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedOption(e.target.value);
  };

  return (
    <>
      <select value={selectedOption} onChange={handleSelectChange}
      className="rounded-lg bg-white p-2 border border-[#192457]
      h-[2.5rem] text-[1.1rem]">
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </>
  );
}



export default DropdownSelect;
