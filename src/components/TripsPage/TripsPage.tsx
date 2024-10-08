import { NoTrips } from "./NoTrips/NoTrips";
import { TripsList } from "./TripsList/TripList";

export const TripsPage = () => {
  const tripCount = 1;
  return <main>{tripCount > 0 ? <TripsList /> : <NoTrips />}</main>;
};
