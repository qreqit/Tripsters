import { useState, useEffect } from "react";
import { GoogleMap, DirectionsRenderer } from "@react-google-maps/api";

// type Params = {
//   startPoint: string;
//   finishPoint: string;
//   additionalPoints: string[];
// };

// {
//   startPoint,
//   finishPoint,
//   additionalPoints,
// }

export const MapComponent= () => {
  const containerStyle = {
    width: "100%",
    height: "600px",
  };

  const center = {
    lat: 50.4501,
    lng: 30.5234,
  };

  const [directions, setDirections] =
    useState<google.maps.DirectionsResult | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const directionsService = new window.google.maps.DirectionsService();

    directionsService.route(
      {
        origin: { lat: 50.4501, lng: 30.5234 }, // Київ
        destination: { lat: 38.7169, lng: -9.1399 }, // Лісабон
        waypoints: [
          {
            location: { lat: 41.9028, lng: 12.4964 }, // Рим
            stopover: true,
          },
          {
            location: { lat: 43.7102, lng: 7.262 }, // Монако
            stopover: true,
          },
          {
            location: { lat: 40.73061, lng: -73.935242 }, // Монако
            stopover: true,
          },
        ],
        travelMode: window.google.maps.TravelMode.DRIVING,
      },
      (response, status) => {
        if (status === "OK") {
          setDirections(response);
        } else {
          console.error("Directions request failed", response);
          setError("Не вдалося побудувати маршрут");
        }
      }
    );
  }, []);

  return (
    <GoogleMap
      mapContainerStyle={containerStyle}
      center={center}
      zoom={5}
      options={{ streetViewControl: false }}
    >
      {directions && (
        <DirectionsRenderer
          options={{
            directions: directions,
          }}
        />
      )}

      {error && <div>{error}</div>}
    </GoogleMap>
  );
};
