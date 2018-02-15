# Gielda
Projekt na zaliczenie symulujący działanie giełd papierów wartościowych

Uruchamiając program tworzą się startowo: Rynek Walut, Rynek Surowców, jedna giełda, jeden inwestor, jeden fundusz, waluta PLN, surowiec oraz dwie spółki.
Można również wczytać większa instancje klikając w menu Zapisz/Wczytaj -> Wczytaj.

Istnieje jeden rynek walut i jeden rynek surowców na cały program.
Waluta PLN jest główną walutą, wszystkie inne waluty mają wartośc zależną od PLN.

Button "Odśwież" odświeża wartości na listach.
Button "Stwórz" otwiera nowe okno, w którym możemy stworzyć giełde, walute, surowiec, fundusz, inwestora, spółke oraz indeks.
Żeby stworzyć spółkę musimy wybrac z listy nad buttonem "Spółka" giełde, na której ma się stworzyć spółka.
Żeby stworzyć indeks, z listy nad buttonem "Indeks" zaznaczamy dowolną liczbę spółek i klikamy.

W głownym oknie klikając na obiekt na liście inwestorów/funduszy/spółek/indeksów/aktywów pojawia się informacja o danym obiekcie w polu pod labelem "Info".
Żeby usunąć obiekt, którego informacje się wyświetlają klikamy button "Usuń".
W celu zapisania aktualnego stanu, klikamy button "Stop". Na samym dole pod listą inwestorów jest pasek stanu. Gdy wątek coś robi to pasek stanu się zmienia. Zapisujemy wtedy, gdy pasek stnau przestanie się zmieniać, co oznacza, że wszystkie wątki są zastopowane. W celu zapisania wybieramy z menu Zapisz/Wczytaj -> Zapisz.

Wartości akcji, surowców, walut zmieniają się losowo.

Klikając w menu "Rynki" możemy wybrać aktualną giełde, której spółki i aktywa chcemy oglądać.

Waluty i surowce są dostępne z każdej giełdy.

Indeksy są międzygiełdowe (giełdy z różnych giełd mogą należec do tego samego indeksu).

W celu wykupienia akcji spółki, zaznaczamy spółke na liście, wpisujemy kwotę i klikamy "Wykup". Jeśli podana kwota będzie większa niż wartośc wszystkich akcji spółki to pojawi się nowe okno pokazujące błąd. Należy wtedy kliknąc na button.

Wątek inwestora oraz funduszu w losowych odstepach czasu kupuje aktywa, sprzedaje i zwiększa swój budżet.

W moim założeniu inwestor oraz fundusz kupują aktywa zawsze z giełdy, a nie od siebie samych.

Wątek spółki w losowych odstępach czasu aktualizuje wartość swoich akcji oraz generuje przychód i zysk.

Po kliknięciu buttona "Stop" i zastopowaniu wszystkich wątków, możemy kliknąć button "Kontynuuj" i wątki będą wykonywane dalej.

Informacje aktualnie używanej giełdy wyświetlają się cały czas.

Niestety nie dałem rady zrobić wykresu wartości akcji w czasie.

Spakowany jest cały folder z projektem. 

W folderze z projektem znajduje się .jar o nazwie "run".

Ps. stopując wątki, po kliknięciu buttona "Stop", dla pewności po chwili czasu można go kliknąć jeszcze raz. (Dla jeszcze większej pewności można większa ilośc razy).
