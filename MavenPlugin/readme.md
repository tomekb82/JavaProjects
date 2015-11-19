generacja:   
 mvn archetype:generate \
    -DgroupId=sample.plugin \
    -DartifactId=hello-maven-plugin \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-plugin

uruchomienie:
mvn sample.plugin:hello-maven-plugin:touch
