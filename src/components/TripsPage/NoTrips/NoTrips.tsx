import { Link } from "react-router-dom";

import './NoTrips.scss';

export const NoTrips = () => {
  return (
    <div className="noTrips">
      <h1 className="noTrips_header">You dont have any trips yet {":{"}</h1>
      <Link to={'create'} className="noTrips_button">Create trip</Link>
    </div>
  );
}