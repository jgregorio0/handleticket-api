# Receipt Parser - Google Vision - Google Sheets API
> Google https://cloud.google.com/vision/docs/libraries?hl=es-419
> 
> Medium https://medium.com/@bhandarimansubh/getting-started-with-google-cloud-vision-api-with-java-cacc0b2defa0

## Habilita la API Vision
1. En la página de selección de proyectos de Cloud Console, selecciona o crea un proyecto de Cloud.
Habilita las Vision API.
1. Crear cuenta de servicio en google console
1. En Cloud Console, ve a la página Crear una clave de cuenta de servicio.
1. Ir a la página Crear clave de la cuenta de servicio
1. En la lista Cuenta de servicio, selecciona Cuenta de servicio nueva.
1. Ingresa un nombre en el campo Nombre de cuenta de servicio.
1. No selecciones un valor de la lista Función. No se necesita una función para acceder a este servicio.
1. Haz clic en Crear. Aparece una nota que advierte que esta cuenta de servicio no tiene función.
1. Haz clic en Crear sin función. Se descargará un archivo JSON que contiene tus claves a tu computadora.

## Configura la variable de entorno GOOGLE_APPLICATION_CREDENTIALS
export GOOGLE_APPLICATION_CREDENTIALS="[PATH]"


## Dependencias maven
```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.google.cloud</groupId>
            <artifactId>libraries-bom</artifactId>
            <version>13.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <dependency>
        <groupId>com.google.cloud</groupId>
        <artifactId>google-cloud-vision</artifactId>
    </dependency>
</dependencies>
```

## Build docker iamge
```
sudo docker build -t handleticket-api:1.0 .
```

## Run docker con google api credentials
```
$(sudo docker run -d --name handleticket-api \
-p 8080:8080 \
-e GOOGLE_APPLICATION_CREDENTIALS=/tmp/keys/googleAppCredentials.json \
-v /home/jgregorio/projects/handleticket-api/.keys/googleApiCredentials.json:/tmp/keys/googleAppCredentials.json:ro \
handleticket-api)
```

## Deploy to Heroku
1. Login 
```
heroku login
```

2. Create heroku app
```
heroku create handleticket-api
https://handleticket-api.herokuapp.com/ | https://git.heroku.com/handleticket-api.git
```

3. Deploy master
```
git push heroku master
```

# Docker compose / Postgres & Adminer
```
cd env/docker-compose
sudo docker-compose up
```

## Adminer
- postgreSQL
- db
- root
- 1234
- postgresdb

# Run app dev
1. application-dev.properties

2. application-imp.properties

3. run profile dev + debug 
```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```

- adding Google as environment variable with maven
```
<plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <environmentVariables>
                        <GOOGLE_APPLICATION_CREDENTIALS>google-credentials.json</GOOGLE_APPLICATION_CREDENTIALS>
                        <GOOGLE_CREDENTIALS>
                            {
                            "type": "service
                            "project_id": "ocr
                            "private_key_id": "ce
                            "private_key": "--
                            "client_email": "jgre
                            "client_id": "1012
                            "auth_uri": "http
                            "token_uri": "htt
                            "auth_provider_x509_c
                            "client_x509_cert_url
                            }
                        </GOOGLE_CREDENTIALS>
                    </environmentVariables>
                </configuration>
            </plugin> 
```

4. remote connect for debugging

# Postgres Database
spring.jpa.database=POSTGRESQL

#Deploy to HEROKU
1. Login
```
heroku login
```

2. Create app (db hobby-dev is created by default)
```
heroku create handleticket-api
git push heroku master
```

3. Active imp profile
```
heroku config:set SPRING_PROFILES_ACTIVE="imp"
```

4. Active google API credentials
```
heroku buildpacks:set https://github.com/elishaterada/heroku-google-application-credentials-buildpack.git -a handleticket-api
heroku config:set GOOGLE_APPLICATION_CREDENTIALS="google-credentials.json"
heroku config:set GOOGLE_CREDENTIALS="{
  "type": "service_
  "project_id": "oc
  "private_key_id":
  "private_key": "-
  "client_email": "
  "client_id": "101
  "auth_uri": "http
  "token_uri": "htt
  "auth_provider_x5
  "client_x509_cert
}"
```

4. Check logs
```
heroku logs --tail
```

