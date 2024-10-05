import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import App from "../App";
import { Home } from "./home/Home";

export const RouteManager = () => {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<App/>}>
        <Route path="home" element={<Navigate to='/'/>}></Route>
        <Route index element={<Home/>} />
      </Route>
    </Routes>
  </BrowserRouter>
  )
};
