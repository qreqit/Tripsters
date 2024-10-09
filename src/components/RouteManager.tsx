import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import App from "../App";
import { Home } from "./home/Home";
import { TripsPage } from "./TripsPage/TripsPage";
import { CreateTrip } from "./TripsPage/CreateTrip/CreateTrip";
import { TripDetails } from "./TripDetails/TripDetails";

export const RouteManager = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path="home" element={<Navigate to="/" />}></Route>
          <Route index element={<Home />} />
          <Route path="trips" element={<TripsPage />}></Route>
          <Route path="trips/create" element={<CreateTrip />}></Route>
          <Route path="tripDetails" element={<TripDetails/>}></Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
