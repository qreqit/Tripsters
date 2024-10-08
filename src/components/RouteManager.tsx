import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import App from "../App";
import { Home } from "./home/Home";
import { TripsPage } from "./TripsPage/TripsPage";

export const RouteManager = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path="home" element={<Navigate to="/" />}></Route>
          <Route index element={<Home />} />
          <Route path="trips" element={<TripsPage />}></Route>
          <Route path="trips/create" element={<p>loh</p>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
