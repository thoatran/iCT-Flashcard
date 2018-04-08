var  fs = require('fs');

var wordListOnlyWord = []; // only word
var wordList = []; // word list with ipa and meaning

var word = "";
var ipapron = "";
var meaning = "";

function WordObject(word, ipapron, meaning) {
    this.word = word;
    this.ipapron = ipapron;
    this.meaning = meaning;
}

fs.readFileSync('./EV_text_dict.txt').toString().split('\n').forEach(function (line) {
    if (line == "") {
        if (word != "") {
            meaning += "<br>";
        }
    } else {
        if (line[0] == "@" || line == "") {

            if (word != "") {

                word = word.trim();
                ipapron = ipapron.trim();

                alreadyAdded = false;
                for (let i = 0; i < wordList.length; i++) {
                    if (wordList[i].word == word) {
                        wordList[i].meaning += meaning;
                        alreadyAdded = true;
                    }
                }

                console.log("WORD: " + word);


                if (!alreadyAdded) {
                    let wordObject = new WordObject(word, ipapron, meaning);
                    wordList.push(wordObject);
                    wordListOnlyWord.push(word.substr(0, word.length)); // force copy string
                }

                word = "";
                ipapron = "";
                meaning = "";

            }
            
            let splitPosition = -1;
            for (let i = 0; i < line.length; i++) {
                if (line[i] == "/") {
                    splitPosition = i;
                    break;
                }
            }

            if (splitPosition == -1) {
                word = line.substr(1, line.length-1).trim();
            } else {
                word = line.substr(1, splitPosition-1);
                ipapron = line.substr(splitPosition, line.length - splitPosition);
            }

        } else {
            if (meaning == "") {
                meaning = line;
            } else {
                meaning = meaning + '<br>' + line;
            }
        }
    }
});



fs.writeFileSync('./dict_data.json', JSON.stringify(wordList) , 'utf-8');

wordListOnlyWord.sort();
fs.writeFileSync('./wordlist-ev.json', JSON.stringify(wordListOnlyWord) , 'utf-8');


console.log("The file was saved!");