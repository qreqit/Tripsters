import { useEffect, useState } from "react";
import "./CreateTrip.scss";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import PlacesAutocomplete from "react-places-autocomplete";
import { IoCloseCircleOutline } from "react-icons/io5";

export const CreateTrip = () => {
  const [tripName, setTripName] = useState("");
  const [startDate, setStartDate] = useState<Date | null>(new Date());
  const [finishDate, setFinishDate] = useState<Date | null>(null);
  const [startPoint, setStartPoint] = useState("");
  const [finishPoint, setFinishPoint] = useState("");
  const [additionalPoint, setAdditionalPoint] = useState("");
  const [aditionalPointsArray, setAdditionalPointsArray] = useState<string[]>(
    []
  );

  useEffect(() => {
    if (startDate === null) {
      setStartDate(new Date());
    }
  }, [startDate]);

  return (
    <main className="createTrip">
      <h1 className="createTrip__header">Insert data to create your trip!</h1>

      <h3 className="createTrip__text">
        You will be able to change this data later.
      </h3>

      <div className="createTrip__block">
        <label htmlFor="name" className="createTrip__block--label">
          Name*
        </label>
        <input
          type="text"
          className="createTrip__block--input"
          placeholder="Enter trip name"
          id="name"
          value={tripName}
          onChange={(e) => setTripName(e.target.value)}
        />
      </div>

      <div className="createTrip__block">
        <label htmlFor="date" className="createTrip__block--label">
          Start date*
        </label>

        <DatePicker
          selected={startDate}
          onChange={(date: Date | null) => setStartDate(date)}
          dateFormat="dd/MM/yyyy"
          placeholderText="Select a start date"
        />
      </div>

      <div className="createTrip__block">
        <label htmlFor="date" className="createTrip__block--label">
          Finish date
        </label>

        <DatePicker
          selected={finishDate}
          onChange={(date: Date | null) => setFinishDate(date)}
          dateFormat="dd/MM/yyyy"
          placeholderText="Select a start date"
        />
      </div>

      <div className="createTrip__block">
        <label htmlFor="startPoint" className="createTrip__block--label">
          Start point*
        </label>

        <PlacesAutocomplete
          value={startPoint}
          onChange={setStartPoint}
          onSelect={(v) => setStartPoint(v)}
        >
          {({
            getInputProps,
            suggestions,
            getSuggestionItemProps,
            loading,
          }) => (
            <div>
              <input
                id="startPoint"
                {...getInputProps({
                  placeholder: "Select start location",
                })}
              />
              <div className="suggestions">
                {loading ? <div>Loading...</div> : null}

                {suggestions.map((suggestion) => {
                  const style = suggestion.active
                    ? { backgroundColor: "#a8dadc", cursor: "pointer" }
                    : { backgroundColor: "#ededed", cursor: "pointer" };
                  return (
                    <div
                      {...getSuggestionItemProps(suggestion, { style })}
                      key={suggestion.placeId}
                    >
                      {suggestion.description}
                    </div>
                  );
                })}
              </div>
            </div>
          )}
        </PlacesAutocomplete>
      </div>

      <div className="createTrip__block">
        <label htmlFor="finishPoint" className="createTrip__block--label">
          Finish point
        </label>

        <PlacesAutocomplete
          value={finishPoint}
          onChange={setFinishPoint}
          onSelect={(v) => setFinishPoint(v)}
        >
          {({
            getInputProps,
            suggestions,
            getSuggestionItemProps,
            loading,
          }) => (
            <div>
              <input
                id="finishPoint"
                {...getInputProps({
                  placeholder: "Select finish location",
                })}
              />
              <div className="suggestions">
                {loading ? <div>Loading...</div> : null}

                {suggestions.map((suggestion) => {
                  const style = suggestion.active
                    ? { backgroundColor: "#a8dadc", cursor: "pointer" }
                    : { backgroundColor: "#ededed", cursor: "pointer" };
                  return (
                    <div
                      {...getSuggestionItemProps(suggestion, { style })}
                      key={suggestion.placeId}
                    >
                      {suggestion.description}
                    </div>
                  );
                })}
              </div>
            </div>
          )}
        </PlacesAutocomplete>
      </div>

      <div className="createTrip__block">
        <label htmlFor="additionalPoints" className="createTrip__block--label">
          Additional points
        </label>

        <div className="additional">
          <PlacesAutocomplete
            value={additionalPoint}
            onChange={setAdditionalPoint}
            onSelect={(v) => {
              setAdditionalPointsArray((prev) => [...prev, v]);
              setAdditionalPoint("");
            }}
          >
            {({
              getInputProps,
              suggestions,
              getSuggestionItemProps,
              loading,
            }) => (
              <div className="additional-search">
                <input
                  id="additionalPoints"
                  {...getInputProps({
                    placeholder: "Add location to your trip",
                  })}
                />
                <div className="suggestions">
                  {loading ? <div>Loading...</div> : null}

                  {suggestions.map((suggestion) => {
                    const style = suggestion.active
                      ? { backgroundColor: "#a8dadc", cursor: "pointer" }
                      : { backgroundColor: "#ededed", cursor: "pointer" };
                    return (
                      <div
                        {...getSuggestionItemProps(suggestion, { style })}
                        key={suggestion.placeId}
                      >
                        {suggestion.description}
                      </div>
                    );
                  })}
                </div>
              </div>
            )}
          </PlacesAutocomplete>

          <div className="additional-block">
            {aditionalPointsArray.map((value) => (
              <div className="additional-block-element">
                <p className="additional-block-element--text">{value}</p>
                <p
                  className="additional-block-element--button"
                  onClick={() => {
                    setAdditionalPointsArray((prev) =>
                      prev.filter((v) => v !== value)
                    );
                  }}
                >
                  <IoCloseCircleOutline />
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>

      <button className="createTrip__button"
      onClick={() => {}}>
          Create trip
        </button>
    </main>
  );
};
