package main.java.test;

public class CarDataEnum {
    public enum CAR1  {

        MAKE("BMW"),
        MODEL("1 SERIES DIESEL COUPE - 120d M Sport 2dr"),
        YEAR("2008");

        String value;

        CAR1( String value ) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }


    public enum CAR2  {

        MAKE("Volkswagen"),
        MODEL("Golf 1.5 TSI EVO SE Nav SG18 HTN"),
        YEAR("2018");

        String value;

        CAR2( String value ) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public enum CAR3  {

        MAKE("TOYOTA"),
        MODEL("YARIS HATCHBACK - 1.0 VVT-i T2 3dr"),
        YEAR("2008");

        String value;

        CAR3( String value ) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public enum CAR4  {

        MAKE("SKODA"),
        MODEL("SUPERB DIESEL ESTATE - 2.0 TDI CR 190 Sport Line 5dr DSG"),
        YEAR("2017");

        String value;

        CAR4( String value ) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}

