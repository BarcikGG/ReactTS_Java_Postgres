import { Link } from "react-router-dom";
import logo from "../assets/Mercedes-Logo.png";

export default function Navigation() {
  return (
    <nav className="flex justify-between items-center h-[5rem] px-10 bg-[#0C1149] text-white">
      <div className="flex items-center text-xl">
        <Link to='/' className="flex">
          <h3>MERCEDES</h3>
          <img src={logo} className="mx-2 w-[1.7rem] h-[1.7rem]"></img>
          <h3>BENZ</h3>
        </Link>
      </div>
      <span className="text-xl">
        <Link to='/Catalog'><p>All cars</p></Link>
      </span>
    </nav>
  )
}
