# rock-paper-scissors


## Make sure that java 11 or above is installed!!

### To run the code:
1. Clone the repo using ```git clone https://github.com/ZaidRazaKhan/rock-paper-scissors.git```
2. Then run ```cd rock-paper-scissors```
3. The follow either of the following: local/docker 

### For building the code on local:
```mvn install```
<br>
```mvn clean```
<br>
```mvn compile```
<br>
```mvn package```
<br>
### For running the code on local:
``` java -cp target/rock-paper-scissor-1.0-SNAPSHOT.jar Main```


### For building the docker image
```
 sudo docker build -f Dockerfile -t rps .
```

### For running the docker image
```
sudo docker run -it rps  java -Djava.util.logging.config.file=logging.properties  -cp target/rock-paper-scissor-1.0-SNAPSHOT.jar Main
```


### For running the unit tests on local
``` mvn clean test ```

### For running the unit tests on docker
```
sudo docker run -it rps  mvn test
```