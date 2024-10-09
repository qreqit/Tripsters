import { TripInfo } from "./TripInfo/TripInfo";
import pic from "../../../pics/home-about2.jpg";
import "./TripList.scss";
import { Trip } from "../../../types/Trip";
import { Link } from "react-router-dom";

const tripListExample: Trip[] = [
  {
    name: "tripname",
    startPoint: "Kyiv",
    finishPoint: "London",
    startDate: "01.01.2024",
    finishDate: "15.01.2024",
    aditionalPoints: ["Warsaw", "Berlin", "Madrid"],
    members: [],
    link: "",
    owner: {
      name: "Reaffith",
      dateOfBirth: "11.04.2006",
      id: 1,
      profilePic: pic,
      trips: [],
      friends: [],
    },
    status: "in progress",
  },
  {
    name: "tripname",
    startPoint: "Kyiv",
    finishPoint: "London",
    startDate: "01.01.2024",
    finishDate: "15.01.2024",
    aditionalPoints: ["Warsaw", "Berlin", "Madrid"],
    members: [],
    link: "",
    owner: {
      name: "Reaffith",
      dateOfBirth: "11.04.2006",
      id: 1,
      profilePic: pic,
      trips: [],
      friends: [],
    },
    status: "incoming",
  },
  {
    name: "tripname",
    startPoint: "Kyiv",
    finishPoint: "London",
    startDate: "01.01.2024",
    finishDate: "15.01.2024",
    aditionalPoints: ["Warsaw", "Berlin", "Madrid"],
    members: [],
    link: "",
    owner: {
      name: "Reaffith",
      dateOfBirth: "11.04.2006",
      id: 1,
      profilePic: pic,
      trips: [],
      friends: [],
    },
    status: "completed",
  },
];

export const TripsList = () => {
  return (
    <>
      <div className="triplist">
        {tripListExample.map((trip) => (
          <TripInfo trip={trip} />
        ))}

        <Link to={"create"} className="triplist__button">
          Create trip
        </Link>
      </div>
    </>
  );
};
