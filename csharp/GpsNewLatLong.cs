using System;

public class GpsNewLatLong {

	public static void Main()
	{
		GpsReading oldReading = new GpsReading(null, DateTime.Now.ToUniversalTime(),
			46.03695, -118.67283, (float) 90, 5, 0);

		Console.WriteLine("Before: " + oldReading);

		GpsReading reading = GetNewLatLong(oldReading, 45, 1000);

		Console.WriteLine("After: " + reading);
	}

		/// <remarks>
		/// Taken from http://www.movable-type.co.uk/scripts/latlong.html
		/// </remarks>
		private static GpsReading GetNewLatLong(GpsReading oldReading, double heading, double dist)
		{
			double EarthRadiusMeters = 6371000d;
			double angularDist = dist / EarthRadiusMeters;
			double newHeadingDeg = oldReading.Heading + heading;
			if (newHeadingDeg >= 360) {
				newHeadingDeg -= 360;
			}
			if (newHeadingDeg < 0) {
				newHeadingDeg += 360;
			}

			//* Convert to radians
			double newHeading = newHeadingDeg * Math.PI / 180;
			double lat = oldReading.Latitude * Math.PI / 180;
			double lon = oldReading.Longitude * Math.PI / 180;

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
				lat2, lon2, (float) newHeadingDeg, (float) oldReading.Speed, oldReading.Odometer);

			return reading;
		}



}

	public class GpsReading
	{
		public AirLinkPacket RawData;
        public DateTime TimeStampUTC;
        public double Latitude;
        public double Longitude;
        public float Heading;
        public double Speed;
		public long Odometer;
		public int Satellites;
		public bool Fix; //Tracking/Not Tracking

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

		public override string ToString()
        {
			string str = String.Format("{0}, {1}, {2}, {3}, {4}, {5}", TimeStampUTC, Latitude, Longitude, Heading, Speed, Odometer);
			return str;
        }

        public GpsReading() { }

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
	}

	public class AirLinkPacket
	{
	}
