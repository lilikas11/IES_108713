package ua.deti;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.deti.ipma_client.CityForecast;
import ua.deti.ipma_client.IpmaCityForecast; //may need to adapt package name
import ua.deti.ipma_client.IpmaService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //todo: should generalize for a city passed as argument
    private static int CITY_ID_AVEIRO = 1010500;
    private static Logger logger = LogManager.getLogger(WeatherStarter.class);
    

    public static void  main(String[] args ) {

        if(args.length == 1){
        try {
            CITY_ID_AVEIRO = Integer.parseInt(args[0]);
            
        } catch (Exception e) {
            System.err.println(("Argument error! Expect and integer value."));
            logger.error(("Didn't provide an integer for city id"));
            System.exit(1);
        }
        }

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID_AVEIRO);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            logger.info("Got response1");
            IpmaCityForecast forecast = apiResponse.body();
            logger.info("Got response2");

            if (forecast != null) {
                var firstDay = forecast.getData().listIterator().next();

                System.out.printf( "max temp for %s is %4.1f %n",
                        firstDay.getForecastDate(),
                        Double.parseDouble(firstDay.getTMax()));
                
            } else {
                System.out.println( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}