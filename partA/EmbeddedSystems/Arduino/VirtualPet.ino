#include <Wire.h>
#include <Adafruit_RGBLCDShield.h>
#include <utility/Adafruit_MCP23017.h>
#include <EEPROM.h>

Adafruit_RGBLCDShield lcd = Adafruit_RGBLCDShield();

#define RED 0x1
#define WHITE 0x7
#define GREEN 0x2
#define OFF 0x0

//create pictures
uint8_t egg[]={
  B00000,
  B01110,
  B11111,
  B11111,
  B11111,
  B11111,
  B01110,
  B00000
};

uint8_t young[] = {
 B00000, 
 B00000, 
 B01010, 
 B00000,  
 B10001, 
 B01110,
 B00000,
 B00000 
};

uint8_t adult1[] = {
  B00100,
  B00110,
  B01111,
  B01000,
  B11010,
  B10101,
  B10111,
  B10010
};

uint8_t adult2[] = {
  B00100,
  B01100,
  B11110,
  B00010,
  B01011,
  B10101,
  B11101,
  B01001
};

uint8_t adult4[] = {
  B00001,
  B00101,
  B01101,
  B11011,
  B00010,
  B00110,
  B11100,
  B00000
};

uint8_t adult3[] = {
  B10000,
  B10100,
  B10110,
  B11011,
  B01000,
  B01100,
  B00111,
  B00000
};

uint8_t down[]{
  B00000,
  B00000,
  B00000,
  B10001,
  B01010,
  B00100,
  B00000,
  B00000
};

uint8_t deadPet[] = {
  B00100,
  B01110,
  B00100,
  B00100,
  B00100,
  B01110,
  B01110,
  B11111
};

//Starting values for Pet stats
int development=0;
int happiness=2;
int fullness =3;

int ageSeconds = 0; //counts seconds
int ageMinutes = 0; //counts minutes

unsigned long start = millis(); //age timer
unsigned long start2 = millis(); //fullness timer
unsigned long start3 = millis(); //happiness timer 

int page_counter = 1;

void setup() {
  // put your setup code here, to run once:
  lcd.begin(16,2);

  //creates egg, young and adult picture for virtual pet
  lcd.createChar(0, young);
  lcd.createChar(1, egg);
  lcd.createChar(2, adult1);
  lcd.createChar(3, adult2);
  lcd.createChar(4, adult3);
  lcd.createChar(5, adult4);

  //creates down arrow
  lcd.createChar(6, down);

  //creates dead pet 
  lcd.createChar(7, deadPet);
  //checks whether saved pet is present, if it is sets page counter to 3 (load/new pet page)
  //else sets page counter to 1 (starts a new virtual pet)
  if(EEPROM.read(4)!=0 || EEPROM.read(5)!=0){
    page_counter = 3;
  }
  else{
    page_counter = 1;
  }
}

//function to allow user to either load a saved pet or start a new one
void loadNew(){
  //writes left arrow next to load pet, if user clicks left button, saved pet is loaded
  lcd.setCursor(0,0);
  lcd.print("Load Pet");
  lcd.print("<"); 
  //writes right arrow next to load pet, if user clicks right button, new pet is started
  lcd.setCursor(0,1);
  lcd.print("New Pet");
  lcd.print(">");

  uint8_t buttons = lcd.readButtons();
  if(buttons & BUTTON_LEFT){
    lcd.clear();
    //loads saved data of virtual pet if user selects the load pet option
    development=EEPROM.read(2);
    happiness=EEPROM.read(1);
    fullness =EEPROM.read(3);
   
    ageSeconds = EEPROM.read(4);
    ageMinutes = EEPROM.read(5);
    page_counter = 1;
  }
  if(buttons & BUTTON_RIGHT){
    //loads default pet stats if new pet option is selected 
    page_counter = 1;
    lcd.clear();
    start = millis();
    start2 = millis();
    start3 = millis();
  }
}

//function to display age, happiness, development stage, fullness as well as virtual pet sketch
void petStats(){
  //displays egg sketch when development stage is 0, young sketch when development is 1, 
  //and adult when development is 2 in the middle of the screen
  if(development==0){
  lcd.setCursor(8,1);
  lcd.write(1);
  }
  else if(development==1){
    lcd.setCursor(8,1);
    lcd.write(0);
  }
  else if(development==2){
    lcd.setCursor(8,0);
    lcd.write(2);
    lcd.setCursor(9,0);
    lcd.write(3);
    lcd.setCursor(8,1);
    lcd.write(4);
    lcd.setCursor(9,1);
    lcd.write(5);
  }
  //displays pet's age top left side 
  lcd.setCursor(0,0);
  lcd.print(ageMinutes);
  lcd.print(":");
  printDigits(ageSeconds);

  //displays development stage bottom left side
  lcd.setCursor(0,1);
  lcd.print("DS=");
  lcd.print(development);
  lcd.setCursor(4,1);
  lcd.print("^");

  //displays happiness top right side
  lcd.setCursor(12,0);
  lcd.print(">");
  lcd.print("H=");
  lcd.print(happiness);

  //displays fullness of the pet bottom right side
  lcd.setCursor(12,1);
  lcd.print("<");
  lcd.print("F=");
  lcd.print(fullness);
}

//if seconds is less than 10, 0 is added infront of seconds, else seconds is printed
void printDigits(int digits){
  if(digits<10){
    lcd.print('0');
    lcd.print(digits);
  }
  else{
    lcd.print(digits);
  }
}
//counts pets age
void age(){
  //if on the virtual pet page, the age of pet increases, else it is paused
  if(page_counter ==1){
    //every second, second increases by 1 until it reaches 9 minutes 59 seconds
    if(millis() - start>1000){
      if(!(ageSeconds == 59 && ageMinutes == 9)){
        ageSeconds++;
        start = millis();
        if(ageSeconds==60){
          ageSeconds=0;
          ageMinutes = ageMinutes +1;
        }
      }
      else{
        page_counter=4;
      }
    }
  }
}
//when age reaches 5 seconds, development stage is set to 1
void ageFiveSecs(){
  if(ageSeconds==5 && development != 2){
    development=1;
  }
}
//reduce happiness every 7 seconds if development stage is bigger than 0
void reduceHappiness(){ 
  //starts timer for happiness when age reaches 5 seconds
  if(ageSeconds==5 && ageMinutes==0){
    start3 = millis();
  }
  if(millis() - start3 > 7000){
    //checks whether development stage is bigger than 0 and happiness is not equal to 0
    if(happiness!=0){
      happiness = happiness - 1;
      //timer is reset when happiness reduces 
      start3=millis();
    }
  }   
}
//reduce fullness every 11 seconds if development stage is bigger than 0
void reduceFullness(){
  //starts timer for fullness when age reaches 5 seconds
  if(ageSeconds==5 && ageMinutes==0){
    start2 = millis();
  }
  if(millis() - start2>11000){
    //checks whether development is bigger than 0 and fullness is not 0
    if(fullness!=0){
      fullness = fullness - 1;
      //resets timer after fullness increases
      start2=millis();
    }
    //if fullness reaches 0, happiness is set to 0
    if(fullness==0){
      happiness = 0;
    }
  }
}
//allows user to perform different actions e.g. feed pet, play with pet, grow pet
void actions(){
  uint8_t buttons = lcd.readButtons();
  if(development>=1){
    //sets development stage to 2 if up button is pressed and age is greater than 35 seconds,
    //and happiness is greater than 0 and fullness is greater than 2
    if(buttons & BUTTON_UP){
      if((ageSeconds>=35 || ageMinutes>=1)& happiness>=1 & fullness>=3){
        development = 2;
      }
    }
    //feeds pet if left button is pressed
    if(buttons & BUTTON_LEFT){
      //if fullness is less than 4, fullness increases by 1 when left button is pressed
      if(fullness<4){
        fullness = fullness + 1;
        //after fullness increases, the fullness timer is reset
        start2=millis(); 
        //if fullness reaches 4, happiness is set to 0
        if(fullness == 4){
          happiness = 0;
          start2=millis(); 
        }
      }
    }
    //plays with pet if right button is pressed
    if(buttons & BUTTON_RIGHT){
      //if happiness is less than 2 and fullness is bigger or equal to 2, happiness increases by 1
      if(happiness<2 & fullness >=2){
        happiness = happiness + 1;
        //after happiness increases, the happiness timer is reset
        start3=millis();
      }      
    }
  }
}
//creates a menu allowing user to save, delete or start a new pet 
void menu(){
  lcd.setBacklight(WHITE);
  //displays the different labels on the screen with arrows corresponding to different buttons
  lcd.setCursor(0,0);
  lcd.print("Save");
  lcd.print("^");

  lcd.setCursor(9,0);
  lcd.print("Delete");
  lcd.print(">");

  lcd.setCursor(0,1);
  lcd.print("New Pet");
  lcd.print("<");

  lcd.setCursor(9,1);
  lcd.print("Return");
  lcd.write(6);

  uint8_t buttons = lcd.readButtons();
  //if up button is pressed, pet is saved
  if(buttons & BUTTON_UP){
    savePet();
  }
  //if right button is pressed, saved pet is deleted
  if(buttons & BUTTON_RIGHT){
    deletePet();
  }
  //if left button is pressed, new pet is started and page counter is set to 1
  //returns to the virtual pet page with default stats
  if(buttons & BUTTON_LEFT){
    lcd.setBacklight(GREEN);
    lcd.clear();
    lcd.print("Starting New Pet");
    delay(1000);
    lcd.clear();
    page_counter = 1;
    newPet();
  }
}
//saves pet stats
void savePet(){
    EEPROM.write(1, happiness);
    EEPROM.write(2, development);
    EEPROM.write(3, fullness);
    EEPROM.write(4, ageSeconds);
    EEPROM.write(5, ageMinutes);
    lcd.setBacklight(GREEN);
    lcd.clear();
    lcd.setCursor(3,0);
    lcd.print("Pet Saved");
    delay(1000);
    lcd.clear();
}
//deletes saved pet stats and sets it back to default
void deletePet(){
  lcd.clear();
  lcd.setBacklight(RED);
  lcd.setCursor(2,0);
  lcd.print("Deleting Pet");
  for(int i = 0; i< EEPROM.length(); i++){
    EEPROM.write(i,0);
  }
  
  lcd.setBacklight(GREEN);
  lcd.clear();
  lcd.print("Saved Pet Deleted");
  delay(1000);
  lcd.clear();
}
//sets pet stats back to default
void newPet(){
  
  development=0;
  happiness=2;
  fullness =3;
  ageSeconds = 0;
  ageMinutes = 0;
  start = millis();
  start2 = millis();
  start3 = millis();
  petStats();
}
//If pet is either unhappy or hungry, the backlight is set to red on the virtual pet page
void backlight(){
  if((happiness == 0 || fullness == 0)&& page_counter==1){
    lcd.setBacklight(RED);
  }
  else{
    lcd.setBacklight(WHITE);
  }
}

void petDead(){
  if(page_counter==4){
    lcd.clear();
    lcd.setCursor(4,0);
    lcd.print("PET DIED");
    lcd.setCursor(8,1);
    lcd.write(7);
    delay(5000);
    lcd.setBacklight(OFF);
  }
}
void menuChange(){
  //if down button is pressed and page counter is less than 2, page counter increases by 1
  //if it reaches 2 and up button is pressed, page counter is set back to 1
  //alternates between virtual pet page and menu page
  uint8_t buttons = lcd.readButtons();
  if(buttons & BUTTON_DOWN){
    lcd.clear();
    if(page_counter <2){
      page_counter = page_counter +1;
    }
    else{
      page_counter = 1;
    }
  }
}

void loop() {
  menuChange();

  switch(page_counter){
    //shows virtual pet page
    case 1:{
      petStats();
      age();
      ageFiveSecs();
      reduceHappiness();
      reduceFullness();
      actions();
      backlight();
    }
    break;
    //shows menu page
    case 2: {
      menu();
    }
    break;
    //shows the load/start new pet on starting the program if virtual pet is saved
    case 3:{
      loadNew();
    }
    break;
    case 4:{
      petDead();
    }
    break;
  }
}
