let answer = '';
let question ='';
let maxWrong = 5;
let mistakes = 0;
let guessed = [];
let status = '';
let wordStatus = null;
let enabled = false;
let user ='';
let changeLanguageBtn = `<button style="margin-left: 20px"
    class="btn btn-outline-warning" onClick="changeLang()">En/Ru</button>`;
let changeAnswerBtn = `<button style="margin-left: 20px" onClick="changeAnswer()"
                   class = "btn btn-outline-success" >Ответить на другой вопрос</button>`;
let letters = 'ABCDEFGHIJKLMNOPQRSTUWVXYZ';

function changeLang() {
    enabled = !enabled;
    if(enabled) {
        letters = 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ';
    } else {
        letters = 'ABCDEFGHIJKLMNOPQRSTUWVXYZ';
    }
    generateButtons();
}
function changeAnswer() {
    window.location.reload();
}

function generateButtons() {
    let buttonsHTML = letters.split('').map(letter =>
        `
      <button
        class="btn btn-lg btn-primary m-2"
        id='` + letter + `'
        onClick="handleGuess('` + letter + `')"
      >
        ` + letter + `
      </button>
    `).join('');

    let promptButton = `<button class="btn btn-success" onClick="myFunction()">Назвать слово</button>`;
    buttonsHTML+=promptButton;
    buttonsHTML+=changeLanguageBtn;
    buttonsHTML+=changeAnswerBtn;

    document.getElementById('keyboard').innerHTML = buttonsHTML;
}

function handleGuess(chosenLetter) {
    guessed.indexOf(chosenLetter) === -1 ? guessed.push(chosenLetter) : null;
    document.getElementById(chosenLetter).setAttribute('disabled', true);

    if (answer.indexOf(chosenLetter) >= 0) {
        guessedWord();
        questionWord()
        checkIfGameWon();
    }
    else if (answer.indexOf(chosenLetter) === -1) {
        mistakes++;
        updateMistakes();
        checkIfGameLost();
        updateHangmanPicture();
    }
}

function updateHangmanPicture() {
    document.getElementById('gamePic').src = '/js/images/' + mistakes + '.png';
}

function checkIfGameWon() {
    if (wordStatus.toUpperCase() === answer) {
        document.getElementById('keyboard').innerHTML = 'Вы выиграли!!!';
        status="WON";
        sending();
    }
}

function checkIfGameLost() {
    if (mistakes === maxWrong ) {
        document.getElementById('wordSpotlight').innerHTML = 'Правильный ответ был: ' + answer;
        document.getElementById('keyboard').innerHTML = 'Вы проиграли!!!';
        status="LOSS";
        sending();
    }
}
function checkIfGameLostPrompt() {
    if (wordStatus.toUpperCase() !== answer ) {
        document.getElementById('wordSpotlight').innerHTML = 'Правильный ответ был: ' + answer;
        document.getElementById('keyboard').innerHTML = 'Вы проиграли!!!';
        status="LOSS";
        sending();
    }
}

function guessedWord() {
    wordStatus= answer.split('').map(letter => (guessed.indexOf(letter) >= 0 ? letter : " _ ")).join('');

    document.getElementById('wordSpotlight').innerHTML = wordStatus;
}

function questionWord() {

    document.getElementById('question-name').innerHTML = question;
}

function guessedWordPrompt() {
    document.getElementById('wordSpotlight').innerHTML = wordStatus.toUpperCase();
}

function updateMistakes() {
    document.getElementById('mistakes').innerHTML = mistakes;
}

function reset() {
    mistakes = 0;
    guessed = [];
    document.getElementById('gamePic').src = '/js/images/0.png';
    wordStatus = '';

    guessedWord();
    questionWord();
    guessedWordPrompt();
    updateMistakes();
    generateButtons();

}

function myFunction() {
    let result = window.prompt("Пожалуйста введите слово:");
    wordStatus=result;
    guessedWordPrompt();
    checkIfGameWon();
    checkIfGameLostPrompt();

}

function sending(){
    let httpRequest=new XMLHttpRequest();
    httpRequest.open("POST",'http://localhost:8080/api/save');
    httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    httpRequest.send(JSON.stringify({ "answer": answer, "mistakes": mistakes,
                                                      "status":status,"user":user}));
    httpRequest.onload=function (){
        alert(httpRequest.responseText);
    }
    console.log("save inside the js file")
}

document.getElementById('maxWrong').innerHTML = maxWrong;

generateButtons();
questionWord();
guessedWord();