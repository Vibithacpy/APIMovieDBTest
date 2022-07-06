FROM maven

#coping src of your framework
COPY src /home/vibitha/src

#coping pom file of your framework
COPY pom.xml /home/vibitha

#coping testing.xml file of your framework
COPY testng.xml /home/vibitha

#running actual command
RUN mvn -f /home/vibitha/pom.xml clean test -DskipTests=true