import React, { useState } from 'react';
import ModalFormProps from '../models/ModalFormInterface';
import Modal from 'react-modal';

Modal.setAppElement('#root');

const ModalBuy: React.FC<ModalFormProps> = ({ isOpen, onClose }) => {
    const [isFormValid, setIsFormValid] = useState<boolean>(false);
    const [isCheckboxChecked, setIsCheckboxChecked] = useState(false);
    const [formData, setFormData] = useState({
        fullName: '',
        email: '',
        phoneNumber: '',
    });
    
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value, type, checked } = e.target;
        const isValidPhoneNumber = /^\+\d{11}$/.test(formData.phoneNumber.trim());
        // setFormData({ ...formData, [name]: value });

        if (type === "checkbox") {
            setIsCheckboxChecked(checked);
          } else {
            setFormData({ ...formData, [name]: value });
          }
        
        const IsFormValid =
          formData.fullName.trim() !== '' &&
          formData.email.trim() !== '' &&
          isValidPhoneNumber &&
          !isCheckboxChecked
      
        setIsFormValid(IsFormValid);
    };
      
    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log(formData);
        onClose();
    };

    return (
        <Modal
        isOpen={isOpen}
        onRequestClose={onClose}
        contentLabel="Оставить заявку"
        className="fixed top-1/2 left-1/2 
        transform -translate-x-1/2 -translate-y-1/2 
        w-[35rem] h-[40rem] p-4 bg-[#192457] rounded-lg shadow-lg
        text-center text-white flex flex-col"
        >
        <h2 className='text-[2rem] font-sans mt-[1.5rem]'>Заполните форму</h2>
        <form onSubmit={handleSubmit}
        className='mt-[3rem] w-[70%] flex flex-col self-center text-[1.2rem]'>
            <div className='flex flex-col text-left'>
            <label htmlFor="fullName" 
            className='mb-[.5rem] font-semibold'>ФИО</label>
            <input
                type="text"
                id="fullName"
                name="fullName"
                value={formData.fullName}
                onChange={handleInputChange}
                placeholder='Иванов Иван Иванович'
                className='h-[2.5rem] px-[.4rem] bg-[#192457] 
                border border-white rounded-[.2rem] mb-[2rem]'
            />
            </div>
            <div className='flex flex-col text-left'>
            <label htmlFor="email"
            className='mb-[.5rem] font-semibold'>Email</label>
            <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                placeholder='example@gmail.com'
                className='h-[2.5rem] px-[.4rem] bg-[#192457] 
                border border-white rounded-[.2rem] mb-[2rem]'
            />
            </div>
            <div className='flex flex-col text-left'>
            <label htmlFor="phoneNumber"
            className='mb-[.5rem] font-semibold'>Номер телефона</label>
            <input
                type="tel"
                id="phoneNumber"
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleInputChange}
                placeholder='+79779907777'
                className='h-[2.5rem] px-[.4rem] bg-[#192457] 
                border border-white rounded-[.2rem] mb-[2rem]'
            />
            </div>
            <div className='flex text-left text-[.8rem]'>
                <label className="flex items-center">
                    <input
                    type="checkbox"
                    id="agreementCheckbox"
                    name="agreementCheckbox"
                    checked={isCheckboxChecked}
                    onChange={handleInputChange}
                    className="mr-2 bg-[#192457] border-white"
                    />
                    Даю согласие на обработку персональных данных
                </label>
            </div>
            <button type="submit"
            className={`w-full py-2 mt-[3.5rem] rounded-[0.5rem] text-[#192457] 
            ${!isFormValid ? 'bg-gray-500 cursor-not-allowed' : 'bg-white hover:bg-blue-600'}`}
            disabled={!isFormValid}>Подтвердить</button>
        </form>
        </Modal>
    );
};

export default ModalBuy;
