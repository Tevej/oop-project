# DayInGothenBurgInTime (DIGIT) by Tevej (Theodor, Eric, Vidar, Erik, Jacob)

---

# Starting the project

## Run the project with intellij
1. Go to edit configuration...
2. Press the + button.
3. Choose Application.
4. Choose the mainclass se.tevej.game.desktop.DesktopLauncher.
5. The working directory should be **your path to the project**/DayInGothenburgInTime/core/assets.
6. The "Use classpath of module" should be the "desktop_main" package.
7. The java version should be the project default (java 8)
8. Leave the rest as is.
9. Press apply and then ok.
10. Go to View/Tool Windows and press gradle.
11. Now there should be a gradle tab on the right edge of intellij, open this.
12. Press the "Refresh all Gradle projects" (The button with the two arrows in a circle).x
13. Press the play button.

## Run the project with gradle
```gradle desktop:run```

## Build the project with gradle
```gradle build```  
This will also generate reports in the  
```core/build/reports/``` folder.

