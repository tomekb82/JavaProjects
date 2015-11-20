generacja:   
 mvn archetype:generate \
    -DgroupId=sample.plugin \
    -DartifactId=hello-maven-plugin \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-plugin

uruchomienie:
mvn sample.plugin:hello-maven-plugin:touch

wykorzystanie w projekcie Java:
   <plugins>
      <plugin>
        <groupId>sample.plugin</groupId>
        <artifactId>hello-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
          <greeting>Welcome tomek</greeting>
        </configuration>
        <executions>
          <execution>
            <phase>compile</phase>
            <goals>
              <goal>sayhi</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
...

<plugins>
      <plugin>
        <groupId>sample.plugin</groupId>
        <artifactId>hello-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
          <plikWejsciowy>touch.txt</plikWejsciowy>
          <plikZSumaKontrolna>checksum</plikZSumaKontrolna>
          <algorytm>SHA256</algorytm>
        </configuration>
        <executions>
          <execution>
            <phase>compile</phase>
            <goals>
              <goal>checksumFile</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

