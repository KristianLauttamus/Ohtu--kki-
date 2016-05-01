# Miniprojektin raportti
Ohjelmistotuotanto-kurssi, kevät 2016

Äkkiä-tiimi:

* Marko Etelätalo
* Tomi Laurinen
* Kristian Lauttamus
* Mikko Määttä
* Esa Potkonen

## Projektin aloitus ja tiimi
Tiimimme muodostui spontaanista kurssin luentotauolla. Tiimiläisten kokemus yhdessä tehtävistä ohjelmistoprojekteista oli hyvin vaihteleva. Joillekin tämä oli ensikokemus asiaan, kun taas yhdellä jäsenellä on jo 20-vuotinen kokemus ohjelmistoalalta. Vaihtelua oli myös siinä, mistä ohjelmointikielistä tiimillä on kokemusta.

Projekti rekisteröitiin saman tien ja luotiin repositorio. Ripeys heijastuu myös tiimille keksityssä nimessä.

## Yleistä projektin lopputuloksesta
Projektia voi kokonaisuutena luonnehtia onnistuneeksi. Toteutimme jokaisessa sprintissä asiakkaan toiveet aikataulussa. Lisäksi hoidimme muut sprinttien pakolliset vaatimukset, esimerkiksi backlogin pitämisen ja toimivan version ohjelmasta palvelimella.

## Yleistä toimintatavoista
Työ tehtiin käytännössä etätyönä, eli emme kokoontuneet ryhmänä paitsi asiakastapaamisissa ja niiden jälkeen pitämissämme suunnittelupalavereissa. Tapaamisten välillä hoidimme viestinnän pääosin Google Hangoutsilla.

Yhteydenpitotapa osoittautui tässä projektissa riittäväksi, sillä projekti oli pieni ja vain neljän viikon mittainen. Huomattiin kuitenkin, että vähänkin suuremmassa projektissa työn tehokkuutta ja järjestelmällisyyttä voisi parantaa useammilla, vaikka lyhyilläkin yhteisillä tapaamisilla. Se selkeyttäisi työnjakoa ja kunkin käsitystä siitä, millä tavalla konkreettisesti edetään sprintin aikana. Vaikuttaakin perustellulta, että ketterissä menetelmissä korostetaan säännöllisen yhteydenpidon merkitystä varsinkin, kun dokumentointi on vähäisempää vesiputousmalliin verrattuna.

Tiimin toiminta muotoutui aika lailla itseorganisoituen. Tiimiläisten ohjelmointitaustat vaihtelivat. Lisäksi päätimme toteuttaa ohjelman web-sovelluksena, vaikka kaikilla tiimiläisillä ei ollut sellaisesta aiempaa kokemusta. Nämä seikat vaikuttivat siihen, mihin alueisiin kunkin käytännön työ painottui. Jokaisen panos oli kuitenkin hyödyllinen ja vei projektia eteenpäin tavoitteiden mukaisesti.

Projekti opetti ennen kaikkea perusasioita ketterien menetelmien käytännöistä. Seuraavassa kerrotaan lyhyesti sprinteistä ja niissä kohdatuista ongelmista.

## Sprintti 1
Projektiympäristö saatiin nopeasti pystyyn, vaikkakin konfiguroinnissa kohdattiin myös ongelmia. Molemmat pakolliset user storyt toteutettiin, optiona ollut kolmas jäi seuraavaan sprinttiin.

## Sprintti 2
User storyjen toteutuksen lisäksi ohjelman rakenne muutettiin järkevämmäksi siten, että kaikki viitetyypit tulivat mukaan asiakkaan haluaman kolmen sijasta. Aikaa vieviä ongelmia oli easyb-testauksessa, sillä FirefoxDriver ei toiminut yhteen Traviksen kanssa, ja Selenium WebDriverin käyttö oli hankalampaa. Tämä heijastui myöhempiinkin sprintteihin.

## Sprintti 3
Ohjelmaa laajennettin tupla-ID-tarkistuksella ja mahdollisuudella ladata bib-tiedosto viitteiksi.

## Sprintti 4
Sprintti oli normaalia lyhyempi, ja siihen mahtui vielä vappukin. Oli ongelmia Springin kanssa, kun viimeisiä vaadittuja ominaisuuksia lisättiin.

