#!/bin/sh

/usr/lib/node_modules/grunt-cli/bin/grunt build
cat vendor/highstock/highslide/highslide.css >> dist/angular-app.css
cp vendor/d3/bubblechart.cts  dist/
