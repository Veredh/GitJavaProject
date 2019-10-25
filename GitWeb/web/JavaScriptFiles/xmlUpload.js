function LoadFile(event) {
    var file = event.target.files[0];
    var reader = new FileReader();

    reader.onload = function (){
        var content = reader.result;
        console.log(content);

        $.ajax({
            url: "../loadXml",
            data:
                {
                    file: content
                },
            type: 'POST',
            success: function() {}
        });
    };

    reader.readAsText(file);
}
// function loadFile(json) {
//
// }