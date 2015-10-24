#!/bin/sh

cp controller/target/myApp-controller-0.1.jar target/
cp -r controller/target/classes/* target/classes/

cp service/target/myApp-service-0.1.jar target/
cp -r service/target/classes/* target/classes/

cp model/target/myApp-model-0.1.jar target/
cp -r model/target/classes/* target/classes/

cp repository/target/myApp-repository-0.1.jar target/
cp -r repository/target/classes/* target/classes/
