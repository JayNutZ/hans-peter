var features = {
    icon: 'btn_img',
    text: 'btn_txt'
};

function edit() {
    getInput().classList.remove('wrong');
}

function send() {
    var input = getInput();

    if (input.checkValidity() && input.value !== '') {
        request();
    } else {
        input.classList.add('wrong');
    }
}

function request() {
    var mail = getInput().value;
    //ToDo: Send
    console.log('Sending', mail);

}

function getButton() {
    return document.getElementById('submit');
}

function getButtonFeature(feature) {
    return document.getElementById(feature);
}

function getInput() {
    return document.getElementById('mail');
}