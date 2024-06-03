# Modul 8a: Multithreading
# Übungsbeispiel Multithreading: Motoren-Teststand

Übungsbeispiel Multithreading: Motoren-Teststand
Abschlussbedingungen

Auf einem Prüfstand soll ein einfacher PID-Regler für einen Motor gestestet werden, wozu der Regler und das gesamte "System under Test" (also der Motor in Kombination mit dem Regler) schon in Java als die Klassen PidController bzw. SystemUnderTest im Package threading modelliert wurden. In Main.main() sieht man das Resultat: auf 0 rpm (Rotations per Minute/Umdrehungen pro Minute) initialisiert wird der Motor anhand der Regelung auf die Soll-Drehzahl von 500 rpm beschleunigt. Hier ein UML-Diagramm mit der Gesamtstruktur (Setter und Getter sowie unwichtige Elemente wurden weggelassen):

![](https://uploads.mfellner.com/mOQRHUqFgrRp.png)

Die beiden Klassen in blau sind in dieser Aufgabe anzupassen (außerdem sind weitere Klassen zu erstellen). Die Klassen in grau müssen in weiterer Folge nicht genauer beachtet werden: es reicht, sie in der Weise zu benutzen, wie es die beiden Klassen in blau bereits tun.

Der Prüfstand soll folgendermaßen verbessert werden:

- Das SystemUnderTest soll in einem eigenen Thread laufen, wobei die Soll-Drehzahl zunächst auf 0 gesetzt bleibt. Dieser Thread gibt selbst nichts auf der Konsole aus.
- Die Ausgabe soll in einer neuen Klasse SystemMonitor erfolgen, die ebenfalls in einem eigenen Thread läuft.
- Die Kommunikation zwischen den beiden Threads erfolgt durch eine eigene Thread-sichere Klasse RpmChannel.
- Beide Threads sollen auf einen Befehl hin beendet werden können.

Dadurch wird es leicht möglich, die Drehzahl während eines Testlaufs von außen anzupassen. Die Main-Methode soll dazu wie folgt vorgehen:

- Beide Threads werden gestartet.
- Nach 500ms wird die Soll-Drehzahl auf 700 rpm gesetzt.
- Nach weiteren 2 Sekunden wird die Soll-Drehzahl auf 300 rpm gesenkt.
- Nach weiteren 3 Sekunden wird der SystemMonitor-Thread gestoppt.
- Nach weiteren 100ms wird der SystemUnderTest-Thread gestoppt.
- Zuletzt wartet die Main-Methode, bis beide Threads ihre Arbeit tatsächlich beendet haben.

Jede eigene bzw. veränderte Klasse muss einen Dokumentationskommentar mit der Angabe des Autors enthalten!

***
### Resources:

- UML: https://uploads.mfellner.com/mOQRHUqFgrRp.png
- Src File mit dem Code: https://uploads.mfellner.com/NUZDL1WDO843.zip