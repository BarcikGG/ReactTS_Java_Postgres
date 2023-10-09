import './App.css'
import { Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import Navigation from './components/Navigation';
import AllCars from './pages/AllCarsPage';
import Car from './pages/CarPage';
import NotFoundPage from './pages/NotFoundPage';

function App() {

  return (
    <>
      <Navigation/>
      <Routes>
        <Route path="*" element={<NotFoundPage />} />
        <Route path='/' element={ <HomePage/> } />
        <Route path='/Catalog' element={ <AllCars/> } />
        <Route path="/Configure/:id" element={<Car/>} />
        <Route path="/404" element={ <NotFoundPage/> } />
      </Routes>
    </>
  )
}

export default App
