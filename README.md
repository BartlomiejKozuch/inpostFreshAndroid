# FreshInpost Mobile Automation Tests

## Wymagania

- Java 17+
- Maven 3+
- Android SDK z zainstalowanym emulator-em
- Appium Server (v2.x)
- Emulator Android (np. `Pixel_3a_API_30_x86`)
- IntelliJ IDEA (lub inny IDE obsługujący Maven)

---

## ⚙️ Konfiguracja lokalna : 

### 🔁 Co trzeba podmienić, aby testy działały lokalnie?

Edytuj plik `config/config.properties`, aby zawierał poprawne dane Twojego środowiska:

```properties
platform.name=Platforma na której odpalasz testy 
device.name=nazwa emulatora która możesz pobrać za pomocą ADB devices  
automation.name=UiAutomator2
app.path=APK testowanej aplikacji musi być w ścieżce app/xxx.apk 
Możesz podmienić własne .apk, ale pamiętaj zaktualizować ścieżkę w app.path

```

### 🔁 Kroki do uruchomienia testów :

- Uruchom emulator
- Uruchom server appium
- Wejdz w SRC->test->tests-> Wybierz test który chcesz uruchomic ->Kliknij prawym przyciskiem myszy
- -> Wybierz Run -> Test sie odpali bez problemów. 