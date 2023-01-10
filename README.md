# Matador

Hej til den nysgerrige seer, her findes informationer angående vores implementering og udvikling af Matador projekt.

- GUI: Som her benyttes som vores visuelle interface som har samme funktion som det originale brætspil. 
  Herinde findes en visuel repræsentation af brikkerne , terningerne, felter samt feltteksten.
  
- Inde i vores Matador mappe i IntelliJ findes alt kodeudviklingen der er blevet lavet under projektet. 

- Indenunder Main, kan alle klasser og objekter findes.

- Controllers: Her er klasserne Guicontroller og Gamecontroller - Der hver især kontrollerer Gui brættet og selve spillet.

- Gamecomponents: Indenunder Board mappen, står alle feltklasserne og deres funktion, ellers finder man Player klasse, Account klasse, Die klasse, Cup klasse, Cup stub klasse og Cardholder klassen samt deres funktioner.  

- Translators: Her finder man alle spillets beskrivelser, alt fra feltbeskrivelser, spille navne og terningekast. 
  Her finder man Dansk tekst og TextLang med ovenstående indhold, og Text klassen samt dets funktion. 
  Der gøres ikke brug af Engelsk tekst, da dette ikke er et krav. 
  
- Til sidst ses vores Game klasse, der primært sætter gang i spillet via GUI'en
- Indenunder Test kan man finde udførte accepttests (til vores valgte user stories) som er udarbejdet via J-unit test.
