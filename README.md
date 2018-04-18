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
