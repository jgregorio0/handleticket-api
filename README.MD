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
sudo docker build -t handleticket-api .
```

## Run docker con google api credentials
```
$(sudo docker run -d --name handleticket-api \
-p 8080:80 \
-e GOOGLE_APPLICATION_CREDENTIALS=/tmp/keys/googleAppCredentials.json \
-v /home/jgregorio/projects/handleticket-api/.keys/googleApiCredentials.json:/tmp/keys/googleAppCredentials.json:ro \
handleticket-api)
```