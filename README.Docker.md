### Building and running your application

When you're ready, start your application by running:
`docker compose up --build`.

Your application will be available at http://localhost:8080.
Run the application by directly typing command java -jar build\libs\reside-backend-0.0.1-SNAPSHOT.jar this will run the .jar with -jar flag in build\libs\whateverfile.jar


### Deploying your application to the cloud

TO BUILD:
Make sure you have gradle build tool is installed in your pc.
Then to run the build for source code run the command 'grade build'.
This will build the .jar file in your build\libs\reside-backend-0.0.1-SNAPSHOT.jar

TO RUN:
Simply run the jar file now to start the tomcat server to start recieving requests.
java -jar build\libs\reside-backend-0.0.1-SNAPSHOT.jar

First, build your image, e.g.: `docker build -t myapp .`.
If your cloud uses a different CPU architecture than your development
machine (e.g., you are on a Mac M1 and your cloud provider is amd64),
you'll want to build the image for that platform, e.g.:
`docker build --platform=linux/amd64 -t myapp .`.

Then, push it to your registry, e.g. `docker push myregistry.com/myapp`.

Consult Docker's [getting started](https://docs.docker.com/go/get-started-sharing/)
docs for more detail on building and pushing.