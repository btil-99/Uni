//Name: Birute Tilvikaite, ID: B72690, Login: cobt3, Assignement title: CW1 A5SearchEngine

var contents = [ "Loughborough University offers degree programmes and world class research.", "An alternative University", "Yet another University"]
var pages = ["|www.lboro.ac.uk|Loughborough University offers degree programmes and word class research.","!www.xyz.ac.uk!An alternative University" , "%www%Yet another University"]
var web = [ {url : "www.lboro.ac.uk", content: "Loughborough University offers degree programmes and world class research." } , {url: "www.xyz.ac.uk", content:"An alternative University"}, {url:"www", content: "Yet another Univeristy"}]


//function 1 
function index(string,pattern,caseSensitive){
//checks whether the condition is case sensitive and returns the index where pattern starts in string
  if(caseSensitive){
    var x = string.indexOf(pattern);
  return x;
  }
  //else if it's not case sensitive, changes both arguments toUpperCase and compares them for equality
  else{
    string=string.toUpperCase();
    pattern=pattern.toUpperCase();
    var result =string.indexOf(pattern);
    //returns the index where pattern starts in string if pattern found in string
    return result;
  }
} 

//function 2
function idxP1(contents,pattern){
  //loop through the contents array 
  for(var i=0; i<contents.length; i++){
    //both pattern and strings in contents array to lower case since case insensitive
    //each string in contents is checked against pattern for a match 
    //if contents matches pattern, returns the index of the first page in contents that matches pattern
    if(contents[i].toLowerCase().indexOf(pattern.toLowerCase()) != -1)
      return i;
      }
    //if no match is found, -1 is returned
    return -1;
} 

//function 3
function match(string,pattern){
  //changes both string and pattern to lower case since case insensitive 
  string=string.toLowerCase();
  pattern = pattern.toLowerCase();
  //loop through pattern
  for (var i=0;i<pattern.length;i++){
    //checks for letters in pattern
    if('a' <= pattern.charAt(i) && pattern.charAt(i) <= 'z'){
      //if all alphabetical characters in pattern don't exist in string, it returns false 
      if(string.indexOf(pattern.charAt(i)) == -1){
        return false;
    }
      }
  }
  //returns true if all letters of pattern appear in string after the loop ended
  return true;
}

//function 4
 	
function matchContents(contents, pattern){
  //define a variable for empty array to hold indexes of each page in contents that match pattern
  var results=[];
  //changes pattern to lower case since case insensitive
  pattern=pattern.toLowerCase();
  //loop through contents array
  for(var i=0; i<contents.length; i++){
    //changes strings in contents to lower case since case insensitive
    var string=contents[i].toLowerCase();
    //loop through pattern
    for (var j=0;j<pattern.length;j++){
      //checks for letters in pattern
      if('a' <= pattern.charAt(j) && pattern.charAt(j) <= 'z'){
        //if pattern doesn't match string, the loop break 
        if(string.indexOf(pattern.charAt(j)) == -1){
         	break;
         }
      }
    }
    //if pattern does match string, its index is added to the end of results array
    if (j>=pattern.length){
      results.push(i);
    }
  }
  //returns an array of indexes of each page in contents that match pattern
  return results;

}

//function 5

function url1(pages, pattern){
  //pattern to lower case since case insensitive
  pattern = pattern.toLowerCase();
  //loop through pages array
  for (var i = 0; i < pages.length; i++){
    //changes strings in pages to lower case since case insensitive
    var page = pages[i].toLowerCase();
    //splits the strings in pages array at the character of the first index of each string
    var array = page.split(page[0]);
    //loop through array
    for(var j = 2; j < array.length; j++){
      //checks to see if pattern matches content, if it does, returns url part of the first page in pages
      if (array[j].indexOf(pattern)>=0){
        return array[1];
      }
    }
  }
  //returns empty string if content part does not match pattern
  return '';
}

//function 6

function urls(pages,pattern){
  //define a variable for empty array to hold urls from pages for which the corresponding content matches pattern
  var results= [];
  //pattern to lower case since case insensitive
  pattern=pattern.toLowerCase();
  //loop through contents array
  for(var i=0; i<pages.length; i++) {
    //changes strings in pages to lower case since case insensitive
    var page = pages[i].toLowerCase();
    //splits the strings in page array at the character of the first index of each string
    var arr = page.split(page[0]);
    //use function 3 to check if characters in pattern match srings in pages
    if(match(arr[2],pattern)) {
      //if match found, adds url at the end of array 
      results.push(arr[1]);
    }
  }
  //returns an array of urls
  return results  
}

//function 7

function score(string, pattern){
  //changes both string and pattern to lower case since case insensitive 
  string = string.toLowerCase();
  pattern = pattern.toLowerCase();
  //count variable to count the amount of times pattern occurs in string
  var count = 0;
  while (index != -1){
    var index = string.indexOf(pattern,index+1);
    if (index !=-1){
      //counts the number of times pattern occurs in string
      count++;
    }
  }
  //returns the total number of times pattern occurs in string
  return count
}

//function 8

function scores(web,pattern){
  //changes pattern to lower case since case insensitive
  pattern = pattern.toLowerCase();
  //new variable for empty array to hold an array of scores
  var result= [];
  //loop through web
  for(var i=0; i<web.length; i++) {
    //changes the content part of web to lower case since case insensitive 
    var string = web[i].content.toLowerCase();
    //use function 7 to get the score and adds the score at the end of array 
    result.push(score(string, pattern));
  }
  //returns an array of scores for the number of times a pattern occurs in the content part in the array web
  return result;
}
  
//function 9

function urlScores(web,pattern) {
  //changes pattern to lower case since case insensitive
  pattern = pattern.toLowerCase();
  //new variable for empty array to hold an array of records
  var result = [];
  var rec = {};
  //loop through web
  for (var i = 0;i<web.length;i++){
    //changes the content part of web to lower case since case insensitive
    var string = web[i].content.toLowerCase();
    //if score is not 0, a match is found, therefore, url and score value are put into the record 
    if (score(string,pattern) !=0){
      rec = {
        url: web[i].url,
        score: score(string,pattern)
      };
      //record is pushed onto the end of array everytime a match is found
      result.push(rec);
    }
  }
  //returns an array of records containing url and score
  return result;
}

//function 10
function rankedScores(web,pattern) {
  //defines new variable holding an array of records containing url and score from function 9
  var scoresArray = urlScores(web, pattern);  
  //function to swap values
  function swap(a, b) {
     var temp = scoresArray[a];
     scoresArray[a] = scoresArray[b];
     scoresArray[b] = temp;
  }
  //loops through the array from index 0 to length-2
  for(var j=0; j<=scoresArray.length-2; j++){
      //compares scoresArray[j] and scoresArray[j+1], if scoresArray[j] is smaller then swap them
      if (scoresArray[j].score < scoresArray[j + 1].score) {
        swap(j, j + 1);
      }
      }
  //returns an array of records in descending order
  return(scoresArray);
}
