# QuizAppFireBase-MAIN-PROJECT-

Funkcjonalności wprowadzone:
1. Użytkownik ma możliwość utworzenia konta(aktualnie wielu).
2. Użytkownik ma możliwość logowania do systemu za pomocą adresu email oraz hasła.
3. Użytkownikowi po zalogowaniu do sytemu uruchamia się strona z kategoriami quizów.
5. Funkcjonalności związane z rozwiązywaniem quizów.
      * Użytkownik po wciśnięciu kategorii z listy kategorii przechodzi do rozwiązywania quizu.
      * Każde pytanie skałada się z treśi pytania oraz czterech odpowiedzi do wybrania przez użytkownika(Każde pytanie posiada tylko jedną prawidłową odpowiedź).
      * Użytkownik ma określony czas na udzielenie odpowiedzi. W przypadku gdy użytkownik nie wybierze żadnej odpowiedzi, system wybierze losową, złą odpowiedź 
        i przejdzie do następnego pytania.
      * Po przejściu przez wszystkie pytania użytkownik zostaje przekierowany do nowej aktywności gdzie użytkownik informowany jest o swoich statystykach w quizie.
      * Po wciśnięciu przycisku zakończ(Odnośnik do punktu wyżej) użytkownik zostanie przekierowany do kategorii quizów oraz jego statystyki zostaną zapisane w bazie danych.
      * Po rozpoczęciu quizu pojawia się pasek na górze okna. Pasek ten pokazuje ile czasu zostało jeszcze użytkownikowi na wybranie odpowiedzi. 
        Pasek ten od 100% do 46% jest zielony, od 45% do 21% jest pomarańczowy, a od 15% do zera jest czerwony.
        Pasek po zakończonym czasie na udzielenie odpowiedzi, zmienia kolor na zielony i odliczanie następuje jeszcze raz. 
        Użytkownik, który wybrał odpowiedź gdy pasek jest żółty lub czerwony, pasek ten zmienia kolor na zielony.
    
6. Funkcjonalności związane z wyświetlaniem użytkowników.
      * Użytkownik po zalogowaniu do systemu może wyświetlić listę zarejestrowanych użytkowniów.
    

7. Funkcjonalności związane z wyświetlaniem bocznego paska menu.
      * Użytkownik po wciśnięciu ikony w lewym górnym rogu otwiera menu boczne.
      * Menu zawiera:
            * Imię - aktualnie zalogowany użytkownik.
            * Kategorie Quizów - zawiera listę kategorii quizów.
            * Użytkownicy - wyświetla wszystkich użytkowników zarejestrowanych w systemie.
            * O mnie - (aktualnie funkcja wykorzystywana do osobistych testów).


