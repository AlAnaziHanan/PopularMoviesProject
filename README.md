# PopularMoviesProject

Udacity's ADND program PopularMovies project.

## Installation

API_KEY is placed in .MainActivity 
private String createURL(){

        final String API_KEY_QUERY_PARAMETER = "api_key";
        //Place key
        final String API_KEY = "";
        Uri uri = Uri.parse ( BASE_URL );
        Uri.Builder builder = uri.buildUpon ();
        builder.appendQueryParameter ( API_KEY_QUERY_PARAMETER, API_KEY );
        return  builder.toString ();
    }
