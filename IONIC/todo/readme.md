
Steps:

Use new nodeJS
and ...

Instalacja
#npm install -g cordova ionic
#ionic start todo blank
#ionic platform add android

Uruchamianie
#ionic build android
#ionic emulate android

#ionic serve 

#ionic run android

Publikacja:
#cordova plugin rm cordova-plugin-console
#cordova build --release android

Generacja klucza przy wgrywaniu
#keytool -genkey -v -keystore my-release-key.keystore -alias MojeTodos -keyalg RSA -keysize 2048 -validity 10000
#jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore platforms/android/build/outputs/apk/android-release-unsigned.apk MojeTodos
#/home/tomek/Desktop/android-sdk-linux/build-tools/22.0.1/zipalign -v 4 platforms/android/build/outputs/apk/android-release-unsigned.apk MojeTodos.apk
