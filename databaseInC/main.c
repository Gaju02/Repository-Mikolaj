#include <stdlib.h>
#include <conio.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <windows.h>
#include <io.h>
//Definicja struktury
struct Baza // STRUKTURA
{
char nazwa[20];
int identyfikator;
char kaliber[20];
char firma[20];
int rokProdukcji;
char krajPochodzenia[20];
};
void otworzBaze(); //SPIS FUNKCJI
void utworzNowaBaze();
void przeglad();
void sortowanie();
void usunBaze();
void zakoncz();
void menu();
void modifyRecord(FILE *fp, char fileName[20]);
void makeNewRecord(FILE *fp, char fileName[20]);
void deleteRecord(FILE *fp, char fileName[20]);
void modRecordDirectly(int choice, FILE *fp, char fileName[20]);
void menu(){ //MENU
int pozycja;
printf("\n===============================================");
printf("\n| Prosta baza Danych - Gajewski Mikolaj        |");
printf("\n| Wybierz operacje                             |");
printf("\n| 1. Otworz baze danych                        |");
printf("\n| 2. Tworzenie bazy danych                     |");
printf("\n| 3. Przeglad bazy danych                      |");
printf("\n| 4. Sortowanie bazy danych                    |");
printf("\n| 5. Usuwanie bazy danych                      |");
printf("\n| 6. Zakonczenie programu                      |");
printf("\n===============================================");
printf("\n");
scanf("%d",&pozycja);
switch(pozycja){
case 1: otworzBaze();
break;
case 2: utworzNowaBaze();
break;
case 3: przeglad();
break;
case 4: sortowanie();
break;
case 5: usunBaze();
break;
case 6: zakoncz();
break;
}
}
int main() //MAIN
{
printf("\n *MENU LOADING*...");
sleep(1);
fflush(stdin);
fflush(stdout);
menu(); //WYSWIETLANIE I FUNKCJA MENU
return 0;
}
void otworzBaze(){ //OTWORZ BAZE
printf("\n Wybrano operacje : Otworz baze danych");
int i;
FILE *file;
int number;
int buf; /*    */
char defaultName[20]="baza";
char extends[20]=".dat";
char numbersName[20];
char fileName[20];
printf("\n Podaj nazwe bazy : bazaXX.dat, gdzie XX to liczby z zakresu 1->99: ");
while(
	scanf("%s",fileName)!=1 || 
	strlen(fileName)>10 || 
	fileName[0]!='b'|| 
	fileName[1]!='a'|| 
	fileName[2]!='z'|| 
	fileName[3]!='a'|| 
	isdigit(fileName[4])>9 || 
	isdigit(fileName[5])>9 ||
	fileName[6]!='.'||	
	fileName[7]!='d'||	
	fileName[8]!='a'||	
	fileName[9]!='t'){
	printf("\n Blad formatu ! Podaj nazwe jeszcze raz: ");
	fflush(stdin);
}
printf("\n Wczytano fileName: %s", fileName);
file=fopen(fileName,"r+b");
if (file==NULL){
printf("\n Plik %s nie zostal otwarty lub plik nie istnieje",fileName);
menu();
}
printf("\n Plik : %s zostal otwarty.",fileName);
fread(&buf,sizeof(int),1,file);
printf("\n Wczytano liczbe rekordow : %d", buf);
struct Baza base[buf];
fread(base,sizeof(struct Baza),buf,file);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Nazwa:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber: %s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
fclose(file);
menu();
}
void utworzNowaBaze(){ //STWORZ BAZE
printf("\n wybrano operacje : Stworz baze danych");
printf("\n Podaj numer bazy [bazaXX.dat] XX -> numer bazy: ");
char defaultName[20]="baza";
char extends[20]=".dat";
char numbersName[20];
int number;
char fileName[20];
while(scanf("%d",&number)!=1 || number<=0 || number>99){
	printf("\n Blad formatu ! Podaj liczbe jeszcze raz: ");
	fflush(stdin);
}
printf("\n Wczytano : %d",number);
itoa(number,numbersName,10);
printf("\n Wczytano po konwersji chara : %s", numbersName);
strcat(defaultName,numbersName);
strcat(defaultName,extends);
printf("\n Wczytano nazwe bazy: %s", defaultName);
strcpy(fileName,defaultName);
int liczbaRekordow;
int buf;
FILE *file;
int i;
file=fopen(fileName,"w+b");
printf("\n Otwieranie pliku: [%s]...", fileName);
sleep(1);
printf("\n Plki %s pomyslnie otworzony ", fileName);
printf("\n Podaj ilosc rekordow w celu utworzenia bazy danych: ");
scanf("%d",&liczbaRekordow);
struct Baza base[liczbaRekordow];
for(i=0;i<liczbaRekordow;i++)
{
printf("\n Podaj informacje o broni nr : %d", i+1);
fflush(stdin);
printf("\n Name:");
scanf("%s",base[i].nazwa);
printf("\n Id:");
scanf("%d",&base[i].identyfikator);
printf("\n Kaliber:");
scanf("%s",base[i].kaliber);
fflush(stdin);
printf("\n Firma:");
scanf("%s",base[i].firma);
fflush(stdin);
printf("\n Rok Produkcji:");
scanf("%d",&base[i].rokProdukcji);
printf("\n Kraj pochodzenia:");
scanf("%s",&base[i].krajPochodzenia);
}
printf("\n");
fwrite(&liczbaRekordow,sizeof(int),1,file);
for( i =0; i<liczbaRekordow;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
fclose(file);
file=fopen(fileName, "r+");
fseek(file,0,SEEK_SET);
fread(&buf,sizeof(int),1,file);
fread(base,sizeof(struct Baza),buf,file);
printf("\n Liczba rekordow to : %d", buf);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber: %s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
printf("\n Baza danych zapisana !");
printf("\n Wracam do menu...");
sleep(1);
fclose(file);
menu();
}
void przeglad(){ //PRZEGLAD BAZY
printf("\n Wybrano operacje : Przeglad bazy danych ");
int i;
int buf;
FILE *file;
char fileName[20];
printf("\n Podaj plik w formacie *.dat :");
scanf("%s",fileName);
file=fopen(fileName,"r+b");
if (file==NULL){
printf("\n Plik %s nie zostal otworzony",fileName);
system("pause");
return 1;
}
printf("\n Plik : %s zostal otworzony.",fileName);
fread(&buf,sizeof(int),1,file);
struct Baza base[buf];
printf("\n Wczytano liczbe rekordow: %d", buf);
fread(base,sizeof(struct Baza),buf,file);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
int opcje;
printf("\nCo chcesz zrobic ? ");
printf("\n 1. Dopisz biezaca strukture");
printf("\n 2. Modyfikacja biezacej struktury");
printf("\n 3. Usun biezaca strukture");
printf("\n 4. Sortuj biezaca strukture");
printf("\n");
scanf("%d",&opcje);
switch(opcje){
case 1:
printf("\n Wybrano : 1. Dopisz biezaca strukture");
fclose(file);
makeNewRecord(file,fileName);
break;
case 2:
printf("\n Wybrano : 2. Modyfikacja biezacej struktury");
fclose(file);
modifyRecord(file,fileName);
break;
case 3:
printf("\n Wybrano : 3. Usun biezaca strukture");
fclose(file);
deleteRecord(file,fileName);
break;
case 4:
printf("\n Wybrano : 4. Sortuj biezaca strukture");
fclose(file);
sortowanie();
break;
}
menu();
}
void sortowanie(){ // SORTOWANIE ZEWNETRZNE
printf("\n Wybrano operacje : Sortowanie bazy danych");
int i;
FILE *file;
int buf;
int choice;
int j;
char fileName[20];
struct Baza temp;
printf("\n Podaj nazwe pliku w formacie *.dat :");
scanf("%s",fileName);
file=fopen(fileName,"r+");
if (file==NULL){
printf("\n Plik %s nie zostal otworzony",fileName);
system("pause");
return 1;
}
printf("\n Plik : %s zostal otworzony.",fileName);
fread(&buf,sizeof(int),1,file);
printf("\n Wczytano rekordow : %d",buf);
struct Baza base[buf];
fread(base,sizeof(struct Baza),buf,file);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
printf("\n Wedlug czego chcesz posortowac rekordy ? ");
printf("\n");
printf("\n 1. nazwa");
printf("\n 2. Id");
printf("\n 3. Kaliber");
printf("\n 4. Firma");
printf("\n 5. RokProdukcji");
printf("\n 6. krajPochodzenia");
printf("\n");
scanf("%d",&choice);
switch(choice){
case 1:
printf("\n Wybrano : Name ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (strcmp(base[j].nazwa, base[j + 1].nazwa) > 0) {
temp = base[j];
base[j] = base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
case 2:
printf("\n Wybrano : identyfikator ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (base[j].identyfikator > base[j+1].identyfikator) {
temp = base[j];
base[j] = base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
case 3:
printf("\n Wybrano : kaliber ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (strcmp(base[j].kaliber, base[j + 1].kaliber) > 0) {
temp = base[j];
base[j]= base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
case 4:
printf("\n Wybrano : firma ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (strcmp(base[j].firma, base[j + 1].firma) > 0) {
temp = base[j];
base[j] = base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
case 5:
printf("\n Wybrano : rokProdukcji ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (base[j].rokProdukcji > base[j+1].rokProdukcji) {
temp = base[j];
base[j]= base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
case 6:
printf("\n Wybrano : krajPochodzenia ");
for (i = 1; i < buf; i++)
for (j = 0; j < buf - i; j++) {
if (strcmp(base[j].krajPochodzenia, base[j + 1].krajPochodzenia) > 0) {
temp = base[j];
base[j] = base[j + 1];
base[j + 1] = temp;
}
}
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
for( i =0; i<buf;i++){
fwrite(&base[i], sizeof(struct Baza), 1, file);
}
printf("\n Zapisano do pliku!");
fclose(file);
menu();
break;
}
}
void usunBaze(){ //USUN BAZE
printf("\n Wybrano operacje : Usun baze danych");
FILE *fp;
char fileName[20];
printf("\n Podaj plik w formacie *.dat : ");
scanf("%s",fileName);
if (fp = fopen(fileName,"r"))
{
fclose(fp);
remove(fileName);
printf("\n Baza danych usunieta ! ");
}
else{
printf("\n Plik nie istnieje");
}
getchar();
menu();
}
void zakoncz(){
printf("\n Zapisuje plik... Zakonczenie programu");
sleep(1);
exit(1);
}
void modifyRecord(FILE *fp, char fileName[20]){ // MODYFIKOWANIE REKORDU
fp=fopen(fileName,"r+b");
int choice;
int buf;
int i;
fread(&buf,sizeof(int),1,fp);
struct Baza base[buf];
fread(base,sizeof(struct Baza),buf,fp);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
printf("\n Ktory rekord chcesz zmodyfikowac : ");
scanf("%d",&choice);
printf("\n Wybrano rekord : %d", choice);
fclose(fp);
modRecordDirectly(choice,fp, fileName);
}
void makeNewRecord(FILE *fp, char fileName[20]){ //TWORZENIE NOWEGO REKORDU
fp=fopen(fileName,"r+b");
int buf;
FILE *fpTemp;
int ret;
char tempFileName[20]="temp2.dat";
fpTemp=fopen(tempFileName,"w+b");
fread(&buf,sizeof(int),1,fp);
int newRecord=buf+1;
printf("\n Wczytano rekordow: %d",buf);
struct Baza base[buf+1];
int newBuf=buf+1;
int i;
fread(base,sizeof(struct Baza),buf,fp);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
printf("\n Podaj wartosci dla rekordu: %d", buf+1);
fflush(stdin);
printf("\n Name:");
scanf("%s",base[buf].nazwa);
printf("\n Id:");
scanf("%d",&base[buf].identyfikator);
printf("\n Kaliber:");
scanf("%s",base[buf].kaliber);
fflush(stdin);
printf("\n Firma:");
scanf("%s",base[buf].firma);
fflush(stdin);
printf("\n Rok Produkcji:");
scanf("%d",&base[buf].rokProdukcji);
printf("\n Kraj pochodzenia:");
scanf("%s",base[buf].krajPochodzenia);
for(i=0; i<newBuf;i++){
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
fwrite(&newBuf,sizeof(int),1,fpTemp);
for(i=0;i<buf+1;i++)
fwrite(&base[i],sizeof(struct Baza),1,fpTemp);
fclose(fp);
fclose(fpTemp);
remove(fileName);
ret=rename(tempFileName, fileName);
if(ret == 0)
printf("\n Plik zapisano poprawnie !");
else
printf("\n Blad : Plik nie zostal poprawnie zapisany");
menu();
}
void deleteRecord(FILE *fp, char fileName[20]){ //USUN REKORD
fp=fopen(fileName,"r+b");
int ret;
FILE *fpTemp;
int buf;
char tempFileName[20]="temp.dat";
fpTemp=fopen(tempFileName,"w+b");
int choice;
int i;
FILE *newFile;
fread(&buf,sizeof(int),1,fp);
int newRecord=buf-1;
struct Baza base[buf];
fread(base,sizeof(struct Baza),buf,fp);
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
printf("\n Ktory rekord chcesz usunac : ");
printf("\n");
scanf("%d",&choice);
printf("\n Wybrano rekord: %d ",choice);
printf("\n Nowy rekord to : %d",newRecord);
fseek(fp,0,SEEK_SET);
for(i=0;i<buf;i++){
if(i==choice-1)
continue;
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
fwrite(&newRecord, sizeof(int),1,fpTemp);
for(i=0;i<newRecord;i++)
fwrite(&base[i],sizeof(struct Baza),1,fpTemp);
fclose(fp);
fclose(fpTemp);
remove(fileName);
ret=rename(tempFileName, fileName);
if(ret == 0)
printf("\n Plik zapisano poprawnie !");
else
printf("\n Blad : Plik nie zostal poprawnie zapisany");
menu();
}
void modRecordDirectly(int choice, FILE *fp, char fileName[20]){ //MODYFIKOWANIE BEZPOSREDNIE REKORDU (NP. 1,2,3)
printf("\n Podaj nowe dane dla rekordu: %d", choice);
int buf;
FILE *tempFile;
char newFileName[20]="temp4.dat";
int i;
int ret;
fp=fopen(fileName,"r+b");
fread(&buf,sizeof(int),1,fp);
printf("\n Wczytano rekordow: %d", buf);
int newBuf=buf;
struct Baza base[buf];
fread(base,sizeof(struct Baza), buf,fp);
fflush(stdin);
printf("\n Name:");
scanf("%s",base[choice-1].nazwa);
printf("\n Id:");
scanf("%d",&base[choice-1].identyfikator);
printf("\n Kaliber:");
scanf("%s",base[choice-1].kaliber);
fflush(stdin);
printf("\n Firma:");
scanf("%s",base[choice-1].firma);
fflush(stdin);
printf("\n Rok Produkcji:");
scanf("%d",&base[choice-1].rokProdukcji);
printf("\n Kraj pochodzenia:");
scanf("%s",&base[choice-1].krajPochodzenia);
printf("\n Sprawdzenie zawartosci ");
for(i=0;i<buf;i++)
{
printf("\n --------------------------");
printf("\n *Rekord nr: [%d]*", i+1);
printf("\n Name:%s", base[i].nazwa);
printf("\n Id:%d", base[i].identyfikator);
printf("\n Kaliber:%s", base[i].kaliber);
printf("\n Firma:%s",base[i].firma);
printf("\n RokProdukcji:%d",base[i].rokProdukcji);
printf("\n krajPochodzenia:%s",base[i].krajPochodzenia);
}
tempFile=fopen(newFileName,"w+b");
fwrite(&newBuf, sizeof(int),1,tempFile);
for(i=0;i<newBuf;i++)
fwrite(&base[i],sizeof(struct Baza),1,tempFile);
fclose(fp);
fclose(tempFile);
remove(fileName);
ret=rename(newFileName, fileName);
if(ret == 0)
printf("\n Plik zapisano poprawnie !");
else
printf("\n Blad : Plik nie zostal poprawnie zapisany");
menu();
}
