import { useNavigate } from "react-router-dom";
import { Trip } from "../../../../types/Trip";
import classNames from "classnames";

import "./TripInfo.scss";

type Params = {
  trip: Trip;
};

export const TripInfo: React.FC<Params> = ({ trip }) => {
  const {
    name,
    startDate,
    startPoint,
    finishDate,
    finishPoint,
    members,
    aditionalPoints,
    link,
    owner,
    status,
  } = trip;

  const navigate = useNavigate();

  const goToTrip = () => {
    navigate(link);
  };

  return (
    <div className="trip">
      <div className="trip__info">
        <div className="trip__info--owner">
          <img src={owner.profilePic} className="trip__info--owner--pic" />
          <p className="trip__info--owner--name">{owner.name}</p>
        </div>

        <div className="trip__info--details">
          <h2 className="trip__info--details--name">{name}</h2>

          <div className="trip__info--details--block">
            <div className="trip__info--details--block--date">
              <p className="trip__info--details--block--date--text">
                Start date: {startDate}
              </p>

              <p className="trip__info--details--block--date--text">
                Finish date: {finishDate}
              </p>
            </div>

            <div className="trip__info--details--block--members">
              <p className="trip__info--details--block--members--text">
                Members:
              </p>
              <p className="trip__info--details--block--members--text">
                {owner.name} and {members.length}{" "}
                {members.length > 1 ? "friends" : "friend"}
              </p>
            </div>

            <button onClick={goToTrip} className="trip__info--details--block--button">See details</button>
          </div>
        </div>
      </div>
      <div className="trip__route">
        <p className="trip__route--text">{startPoint}</p>

        <div className="trip__route--info">
          <div className="trip__route--info--line">{""}</div>

          <p className="trip__route--info--text">
            {aditionalPoints.length} more{" "}
            {aditionalPoints.length > 1 ? "points" : "point"}
          </p>
        </div>

        <p className="trip__route--text">{finishPoint}</p>
      </div>

      <div className="trip__status">
        <p className="trip__status--text">Status: {status}</p>

        <div
          className={classNames("trip__status--circle", {
            incoming: status === "incoming",
            now: status === "in progress",
            completed: status === "completed",
          })}
        >{''}</div>
      </div>
    </div>
  );
};
