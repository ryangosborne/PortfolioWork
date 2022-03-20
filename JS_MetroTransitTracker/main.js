// IIFE
(() => {

    //create map in leaflet and tie it to the div called 'theMap'
    const map = L.map('theMap').setView([44.650627, -63.597140], 14);

    const mapTile = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

    let busIcon = L.icon({
        iconUrl: '/bus.png',
        iconSize: [29, 29],
        iconAnchor: [14, 29]
    });

    let busLayer = L.geoJSON().addTo(map);


    // functions
    const filterBusRoutes = (json, startRange, endRange) => {
        // function to return filtered bus routes based on range of numbers passed
        return json.entity
        .filter(bus => bus.vehicle.trip.routeId >= startRange && bus.vehicle.trip.routeId <= endRange
        );
    }

    const createGeoJSONObjs = (json) => {
        // function to return geoJSON objects from raw json
        return json
        .map(bus => {
            return {
                type: "Feature",
                properties: {
                    uniqueId : bus.id,
                    tripId: bus.vehicle.trip.tripId,
                    routeId: bus.vehicle.trip.routeId,
                    speed: bus.vehicle.position.speed,
                    bearing: bus.vehicle.position.bearing,
                    vehicleId: bus.vehicle.vehicle.id
                },
                geometry: {
                    type: "Point",
                    coordinates: [bus.vehicle.position.longitude, bus.vehicle.position.latitude],
                }
            }
        });
    }

    const applyStyles = (feature, layer) => {
        // function to apply icon, rotation and pop-up properties to bus
        // handling possible undefined values with ternaries
        layer.setIcon(busIcon),
        layer.setRotationAngle(feature.properties.bearing)
        layer.bindPopup("<strong>Route: " + feature.properties.routeId + "</strong><br>" +
            "Unique ID: " + feature.properties.uniqueId + "<br>Trip ID: " + 
            feature.properties.tripId + "<br>Speed: " + 
            (feature.properties.speed ? feature.properties.speed.toFixed(3) : "0") + 
            "<br>Bearing: " + (feature.properties.bearing ? feature.properties.bearing : "0") +
            "°<br>Vehicle ID: " + feature.properties.vehicleId)
    }

    const populateBuses = () => {
        // function to get raw json, filter results by route, convert to geoJSON objects,
        // apply styles, store in busLayer and add to map
        fetch('https://hrmbusapi.herokuapp.com/')
            .then((response) => response.json())
            .then((json) => {
                filteredBuses = filterBusRoutes(json, 1, 10);
                console.log(filteredBuses);
                formattedBuses = createGeoJSONObjs(filteredBuses);
                console.log(formattedBuses);

                let busLayer = L.geoJSON(formattedBuses, {
                    onEachFeature: applyStyles // callback 
                });
            
                busLayer.addTo(map);
        });
    }

    const refreshBuses = () => {
        (function(){
            map.eachLayer((layer) => {
                if (layer == mapTile) return;
                layer.remove();
              });
            map.removeLayer(busLayer);
            populateBuses();
            setTimeout(arguments.callee, 8000);
        })();
    }

    // runningprogram
    populateBuses();
    refreshBuses();

})() // end IIFE