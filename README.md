## osm17L_projekt1

**Przedmiot:** OSM

**Projekt:** 1

**Zadanie:** 19

**Temat:** Diagnostyka wielotorbielowatości nerek: występowanie torbieli w badaniu USG nerek (wartość logiczna), stężenie albuminy (liczba rzeczywista) oraz liczba erytrocytów w moczu (liczba całkowita).

**Zespół:** Aleksandra Zając, Tomasz Kopacz

**Biblioteki:** brak

**Uwagi dodatkowe:** brak

### Specyfikacja

Pełny opis projektu do wykonania znajduje się [tutaj](http://osm.ire.pw.edu.pl/data/_uploaded/file/2017/Zadanie1.pdf).
Zadanie polega na wykonaniu aplikacji okienkowej, mającej możliwość rejestracji danych pacjenta i jego badania. Wszyscy zarejestrowani pacjenci są widoczni w tabeli. Możliwe jest usunięcie pacjenta oraz zmiana jego danych.

### Struktura programu

Kod programu podzielony jest na 3 główne części, znajdujące się w osobnych pakietach:
* GUI - interfejs zbudowany z głównego okna, w którym umieszczone są panele: Dane Pacjenta, Badanie oraz Lista Pacjentów
* Dane - zawierają zbior pacjentów oraz definicję obiektu pacjenta i badania
* Kontroler - jego zadaniem jest nadzorowanie działania programu na podstawie interakcji z użytkownikiem

![](https://cloud.githubusercontent.com/assets/17621860/24841184/2d950edc-1d7e-11e7-89a1-600721c1cb31.jpg)


#### Okno główne - klasa Window

* layout - klasa Window dziedziczy po klasie JFrame, składa się z paska Menu oraz trzech paneli: pacjenta, badania i listy
* setController(Controller c) - ustawia kontroler dla tego okna
* clearAll() - czyści wszystkie formularze
* clearPatientPanel() - czyści formularz pacjenta
* clearTestPanel() - czyści formularz badania
* clearSelection() - sprawia, że żaden wiersz tabeli nie jest zaznaczony
* showPatientPanel() - uaktywnia panel pacjenta
* showTestPanel() - uaktywnia panel badania
* gettery paneli

#### Panel pacjenta - klasa PatientPanel

* layout - klasa dziedziczy po JPanel, ułożenie elementów grupowe, widoczny podział na dwie kolumny: dla etykiet, dla pól edytowalnych
* konstruktor
* setController(Controller c) - ustawia kontrolera panelu
* setAvailable(boolean b) - uaktywnia panel w celu edycji
* clearFields() - czyści formularz
* gettery dla przycisków i pól edycyjnych

#### Panel badania - klasa TestPanel

* layout - klasa dziedziczy po JPanel, grupowe ułożenie elementów, dwie kolumny: etykiety, pola edycyjne
* konstruktor
* setController(Controller c) - ustawia kontrolera panelu - również kontrola pól edycyjnych w celu zauważenia przekroczonych, patologicznych wartości
* setAvailable(boolean b) - uaktywnia formularz do edycji 
* clearFields() - czyści formularz
* gettery dla przycisków i pól edycyjnych

#### Panel listy - klasa ListPanel

* layout - klasa dziedziczy po JPanel, zawiera tabelę pacjentów
* konstruktor
* setController(Controller c) - ustawia kontrolera dla panelu, oprócz kontroli przycisków kontrola tabeli (zmiana zaznaczenia wiersza)
* clearSelection() - sprawia, że żaden wiersz nie jest zaznaczony
* gettery przycisków i tabeli

#### Imitacja bazy danych - klasa Database

* zbiór pacjentów - HashSet
* add(Patient p) - dodaje nowego pacjenta do zbioru
* get(String pesel) - zwraca pacjenta o podanym peselu ze zbioru (jeśli istnieje)
* set(String pesel, Patient p) - zmienia dane pacjenta ze zbioru o podanym peselu na dane pacjenta p
* contains(String pesel) - sprawdza, czy pacjent o danym peselu znajduje się w zbiorze
* remove(String pesel) - usuwa pacjenta o podanym peselu
* getDatabase()

#### Pacjent - klasa Patient

* pola tekstowe: imię, nazwisko, pesel, płeć, ubezpieczenie
* pole badania: Test
* gettery i settery
* toString()

#### Badanie - klasa Test

* pole daty - Date
* pole obecności torbieli - boolean  
* pole stężenia albuminy - float
* pole liczby erytrocytów - int
* gettery i settery
* toString()

#### Kontroler - klasa Controller

* pole prywatne Window - okno, obsługiwane przez kontroler
* konstruktor
* ActionListener - obsługa przycisków
  * przycisk zapisz pacjenta
  * przycisk anuluj wpis formularza pacjenta
  * przycisk zapisz badanie
  * przycisk anuluj wpis formularza badania
  * przycisk dodaj nowego pacjenta
  * przycisk usuń pacjenta
* ListSelectionListener - działanie programu po zaznaczeniu pacjenta na liście
* ItemListener - obsługa check box'a obecności torbieli: jesli check box jest zaznaczony, to wyświetla tekst na czerowno
* DocumentListener - obsługa pól tekstowych stężenia albumin i liczby erytrocytów: jeżeli liczby większe od 20 wyświatl na czerwono


### Podział zadań

**Aleksandra Zając** - Window, PatientPanel, TestPanel, ListPanel, Patient </br>
**Tomasz Kopacz** - Controller, Database, Test
