# US Census Grails Vue
A simple spa sample (sqlite, grails 3, vue 2).

## Environment

Please install Grails 3.3.0 using [sdkman](http://sdkman.io/).

```
sdk install java
```

## Testing

```
grails test-app
```

## Running

You must pass the **JDBC_CONNECTION_STRING** as an argument in the command line (replace the FULL_PATH_TO_DB with the actual value).

```
grails run-app -DJDBC_CONNECTION_STRING=jdbc:sqlite:FULL_PATH_TO_DB
```

After running you can open in your browser *http://localhost:8080* and select a variable from the list, the application will fetch the distinct values for that variable, the number of rows with that value and the average age. The null values are counted as **N/A** (Not available).
