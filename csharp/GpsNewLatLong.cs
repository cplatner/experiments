using System;

/// <summary>
/// Originally written around 2011-03-17 to calculate a new position, based on a
/// heading and distance.
/// </summary>
public class GpsNewLatLong
{
    public static void Main()
    {
        var oldReading = new GpsReading(null, DateTime.Now.ToUniversalTime(), 46.03695, -118.67283, 90f, 5f, 0);

        Console.WriteLine("Before: " + oldReading);

        var reading = GetNewLatLong(oldReading, 45d, 1000d);

        Console.WriteLine("After: " + reading);
    }

    /// <summary>
    /// Given a current GPS reading, a heading, and a distance traveled, compute
    /// a new GPS reading.
    /// </summary>
    /// <remarks>
    /// Taken from http://www.movable-type.co.uk/scripts/latlong.html
    /// </remarks>
    /// <param name="oldReading">Old GPS reading</param>
    /// <param name="headingInDegrees">Heading in degrees - -360 - 360</param>
    /// <param name="distanceInMeters">Distance traveled in meters</param>
    private static GpsReading GetNewLatLong(GpsReading oldReading, double headingInDegrees, double distanceInMeters)
    {
        const double EarthRadiusMeters = 6371000d;
        double angularDist = distanceInMeters / EarthRadiusMeters;
        double newHeadingDeg = oldReading.Heading + headingInDegrees;
        if (newHeadingDeg >= 360d) {
            newHeadingDeg -= 360d;
        }
        if (newHeadingDeg < 0d) {
            newHeadingDeg += 360d;
        }

        //* Convert to radians
        double newHeading = newHeadingDeg * Math.PI / 180d;
        double lat = oldReading.Latitude * Math.PI / 180d;
        double lon = oldReading.Longitude * Math.PI / 180d;

        double lat2 =
            Math.Asin(Math.Sin(lat) * Math.Cos(angularDist)
                + Math.Cos(lat) * Math.Sin(angularDist) * Math.Cos(newHeading));
        double lon2 = lon
            + Math.Atan2(Math.Sin(newHeading) * Math.Sin(angularDist) * Math.Cos(lat),
                 Math.Cos(angularDist) - Math.Sin(lat) * Math.Sin(lat2));

        // normalise to -180...+180
        lon2 = (lon2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;
        //* Convert back to degrees
        lat2 *= 180 / Math.PI;
        lon2 *= 180 / Math.PI;

        GpsReading reading = new GpsReading(null, DateTime.Now.ToUniversalTime(),
            lat2, lon2, (float)newHeadingDeg, (float)oldReading.Speed, oldReading.Odometer);

        return reading;
    }
}

/// <summary>
/// Hold a single GPS reading, along with any extra data that might be available from
/// the AirLink modem.
/// </summary>
public class GpsReading
{
    public AirLinkPacket RawData { get; set; }
    public DateTime TimeStampUTC { get; set; }
    public double Latitude { get; set; }
    public double Longitude { get; set; }
    public float Heading { get; set; }
    public double Speed { get; set; }
    public long Odometer { get; set; }
    public int Satellites { get; set; }
    public bool Fix { get; set; } //Tracking/Not Tracking

    // Auxiliary data, not set here
    public bool Historic = false;

#if HDOP
		public int HDOP = 0;

		public enum HDOPRatingEnum
        {
            Ideal,
            Excellent,
            Good,
            Moderate,
            Fair,
            Poor,
            VeryPoor
        }

        public HDOPRatingEnum HDOPRating
        {
            get
            {
				// "Horizontal Dilution Of Precision" http://en.wikipedia.org/wiki/HDOP
                if (HDOP == 1) return HDOPRatingEnum.Ideal;
                else if (HDOP > 1 && HDOP <= 3) return HDOPRatingEnum.Excellent;
                else if (HDOP > 3 && HDOP <= 6) return HDOPRatingEnum.Good;
                else if (HDOP > 6 && HDOP <= 8) return HDOPRatingEnum.Moderate;
                else if (HDOP > 8 && HDOP <= 20) return HDOPRatingEnum.Fair;
                else if (HDOP > 20 && HDOP <= 50) return HDOPRatingEnum.Poor;
                else return HDOPRatingEnum.VeryPoor;
            }
        }

        public string HDOPRatingString
        {
            get
            {
                string rating = "";
                switch (HDOPRating)
                {
                    case HDOPRatingEnum.Ideal:      rating = "Ideal";       break;
                    case HDOPRatingEnum.Excellent:  rating = "Excellent";   break;
                    case HDOPRatingEnum.Good:       rating = "Good";        break;
                    case HDOPRatingEnum.Moderate:   rating = "Moderate";    break;
                    case HDOPRatingEnum.Fair:       rating = "Fair";        break;
                    case HDOPRatingEnum.Poor:       rating = "Poor";        break;
                    case HDOPRatingEnum.VeryPoor:   rating = "Very Poor";   break;
                }

                return rating;
            }
		}
#endif

    public GpsReading(AirLinkPacket rawData, DateTime timestampUTC, double lat, double lon, float heading, float speed, long odometer)
    {
        this.RawData = rawData;
        this.TimeStampUTC = timestampUTC;
        this.Latitude = lat;
        this.Longitude = lon;
        this.Heading = heading;
        this.Speed = speed;
        this.Odometer = odometer;
    }

    public override string ToString()
    {
        return string.Format($"{TimeStampUTC}, {Latitude}, {Longitude}, {Heading}, {Speed}, {Odometer}",
            TimeStampUTC, Latitude, Longitude, Heading, Speed, Odometer);
    }
}

//* Dummy class that wouuld normally contain code for parsing AirLink packets
//* from Sierra Wireless modems
public class AirLinkPacket
{
}
